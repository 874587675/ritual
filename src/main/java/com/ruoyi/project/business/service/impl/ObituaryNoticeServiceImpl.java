package com.ruoyi.project.business.service.impl;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.enums.TradeStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.framework.redis.ZSetComponent;
import com.ruoyi.project.business.constant.OrderConstant;
import com.ruoyi.project.business.domain.*;
import com.ruoyi.project.business.mapper.*;
import com.ruoyi.project.business.pay.wechat.common.WxPayCommon;
import com.ruoyi.project.business.pay.wechat.service.impl.WxPayJsapiService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.util.aliyun.oss.OssUtil;
import com.ruoyi.project.business.util.aliyun.sms.SmsUtil;
import com.ruoyi.project.business.util.config.WxPayConfig;
import com.ruoyi.project.business.vo.GiftItemVO;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.service.ObituaryNoticeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class ObituaryNoticeServiceImpl extends ServiceImpl<ObituaryNoticeMapper, ObituaryNotice> implements ObituaryNoticeService {
    @Resource
    private ObituaryNoticeMapper obituaryNoticeMapper;
    @Resource
    private SmsUtil smsUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RandomUtils randomUtils;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private GiftItemMapper giftItemMapper;
    @Resource
    private WxPayJsapiService wxPayJsapiService;
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private ZSetComponent zSetComponent;
    @Resource
    private OrderSuccessMapper orderSuccessMapper;
    @Resource
    private UserTeamMapper userTeamMapper;
    @Resource
    private WalletMapper walletMapper;
    @Resource
    private BalanceChangeMapper balanceChangeMapper;
    @Resource
    private AgencyMapper agencyMapper;
    

    @Override
    public ObituaryNoticeVO selectObituaryNoticeByUserId(Integer pageNo, Integer pageSize, String userId) {
        return obituaryNoticeMapper.selectObituaryNoticeByUserId(new Page<>(pageNo, pageSize), userId);
    }

    @Override
    @Transactional
    public String deleteObituaryNotice(Integer id) {
        try {
            int deleteResult = obituaryNoticeMapper.deleteById(id);
            if (deleteResult <= 0) {
                log.error("删除讣告失败");
                throw new ServiceException("删除讣告失败");
            } else {
                log.info("删除讣告成功");
                return "删除讣告成功";
            }
        } catch (Exception e) {
            log.error("删除讣告失败 {}", e.getMessage());
            throw new ServiceException("删除讣告失败");
        }
    }

    @Override
    public String addObituaryNotice(ObituaryNoticeVO obituaryNoticeVO) {
        boolean result = smsUtil.verifyPhoneCode(obituaryNoticeVO.getPhone(), obituaryNoticeVO.getCode());
        if (!result) {
            log.error("手机验证码验证失败");
            throw new ServiceException("手机验证码验证失败");
        }
        int insertResult = obituaryNoticeMapper.insert(obituaryNoticeVO);
        if (insertResult <= 0) {
            log.error("添加讣告失败");
            throw new ServiceException("添加讣告失败");
        }
        log.info("添加讣告成功");
        return "添加讣告成功";
    }

    @Override
    public ObituaryNoticeVO selectObituaryNoticeById(Integer id) {
        ObituaryNotice obituaryNotice = obituaryNoticeMapper.selectById(id);
        if (obituaryNotice == null) {
            log.error("查询的讣告不存在");
            throw new ServiceException("查询的讣告不存在");
        }
        log.info("查询讣告信息成功");
        ObituaryNoticeVO obituaryNoticeVO = new ObituaryNoticeVO();
        BeanUtils.copyProperties(obituaryNoticeVO, obituaryNotice);
        return obituaryNoticeVO;
    }

    @Override
    public Map<String, String> orderObituaryNotice(WeChatJsapiPayVO weChatJsapiPayVO) {
        try {
            Calendar calendar = null; 
            if (weChatJsapiPayVO == null) {
                throw new ServiceException("下单参数不能为空");
            }
            BigDecimal totalAmount = BigDecimal.valueOf(weChatJsapiPayVO.getAmount().getTotal());
            if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
                throw new ServiceException("金额不能为空");
            }
            Order order = Order.builder()
                    .id(randomUtils.generateNumeric(11))
                    .userId(weChatJsapiPayVO.getGiftItemVO().getUserId())
                    .openId(weChatJsapiPayVO.getPayer().getOpenid())
                    .orderNo("O" + randomUtils.generateNumeric(11))
                    .amount(totalAmount)
                    .subject(OrderConstant.LIGHT_ORDER_ITEM)
                    .orderStatus(OrderStatus.NO_PAY.getCode())
                    .orderStatusDesc(OrderStatus.NO_PAY.getStatus())
                    .tradeStatus(TradeStatus.NOT_PAY.getCode())
                    .tradeStatusDesc(TradeStatus.NOT_PAY.getStatus())
                    .build();
            //创建订单信息
            int result = orderMapper.insert(order);
            //创建供奉礼物的信息
            GiftItemVO giftItemVO = weChatJsapiPayVO.getGiftItemVO();
            User user = userMapper.selectById(giftItemVO.getUserId());
            if (giftItemVO.getValidateDays()!=null){
                // 获取当前时间
                calendar = Calendar.getInstance();
                // 当前时间加上有效天数
                calendar.add(Calendar.DAY_OF_YEAR, giftItemVO.getValidateDays());
            }
            GiftItem giftItem = GiftItem.builder()
                    .itemId(giftItemVO.getItemId())
                    .userId(giftItemVO.getUserId())
                    .nickName(user.getNickname())
                    .count(giftItemVO.getCount())
                    .score(giftItemVO.getScore())
                    .museumId(giftItemVO.getMuseumId())
                    .orderId(order.getId())
                    .orderStatus(OrderStatus.NO_PAY.getCode())
                    .obituaryId(giftItemVO.getObituaryId())
                    .build();
            if (calendar!=null){
                giftItem.setExpireTime(calendar.getTime());
            }
            int giftResult = giftItemMapper.insert(giftItem);
            if (result <= 0 || giftResult <= 0) {
                throw new ServiceException("创建订单失败,请重试");
            } else {
                //设置商户订单号
                weChatJsapiPayVO.setOutTradeNo(order.getOrderNo());
                //设置商品订单描述信息
                weChatJsapiPayVO.setDescription(order.getSubject());
                log.info("创建订单成功：{}", order);
                return wxPayJsapiService.wxPay(weChatJsapiPayVO);
            }
        } catch (Exception e) {
            log.error("创建订单失败：{}", e.getMessage());
            throw new ServiceException("创建订单失败,请重试");
        }
    }

    @Override
    @Transactional
    public String payObituaryNoticeCallback(HttpServletRequest request) {
        log.info("支付通知-开始");
        try {
            String body = IoUtil.getUtf8Reader(request.getInputStream()).readLine();
            RequestParam requestParam = new RequestParam.Builder()
                    .serialNumber(request.getHeader(WxPayCommon.WECHATPAY_SERIAL))
                    .nonce(request.getHeader(WxPayCommon.WECHATPAY_NONCE))
                    .signature(request.getHeader(WxPayCommon.WECHATPAY_SIGNATURE))
                    .timestamp(request.getHeader(WxPayCommon.WECHATPAY_TIMESTAMP))
                    .signType(request.getHeader(WxPayCommon.WECHATPAY_SIGN_TYPE))
                    .body(body)
                    .build();
            RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
            NotificationParser parser = new NotificationParser(config);
            // 以支付通知回调为例，验签、解密并转换成 Transaction
            Transaction transaction = parser.parse(requestParam, Transaction.class);
            log.info("支付通知-结束：{}", JSON.toJSONString(transaction));
            // 业务逻辑处理
            // 1、查询订单信息
            Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>()
                    .eq(Order::getOrderNo, transaction.getOutTradeNo())
                    .eq(Order::getIsDeleted, 0));
            // 2、更新订单状态
            if ("SUCCESS".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                order.setOrderStatus(OrderStatus.PAYED.getCode());
                order.setOrderStatusDesc(OrderStatus.PAYED.getStatus());
                //设置交易状态
                order.setTradeStatus(TradeStatus.SUCCESS.getCode());
                order.setTradeStatusDesc(TradeStatus.SUCCESS.getStatus());
                orderMapper.updateById(order);
                //构建支付成功对象
                OrderSuccess orderSuccess = OrderSuccess.builder()
                        .id(randomUtils.generateNumeric(11))
                        .orderId(order.getId())
                        .currency(transaction.getAmount().getCurrency())
                        .payerCurrency(transaction.getAmount().getPayerCurrency())
                        .payerTotal(BigDecimal.valueOf(transaction.getAmount().getPayerTotal()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))
                        .isDeleted(0)
                        .bankType(transaction.getBankType())
                        .tradeType(transaction.getTradeType().toString())
                        .transactionId(transaction.getTransactionId())
                        .build();
                orderSuccessMapper.insert(orderSuccess);
                //更新供奉礼物的信息
                GiftItem giftItem = giftItemMapper.selectOne(new LambdaQueryWrapper<GiftItem>()
                        .eq(GiftItem::getOrderId, order.getId()));
                giftItem.setOrderStatus(OrderStatus.PAYED.getCode());
                giftItemMapper.updateById(giftItem);
                //更新用户积分(用户在当前房间的排行和用户在总的排行)
                zSetComponent.sendGift(giftItem.getUserId(), giftItem.getScore());
                // 收入分销(直接推荐人 提成20%)
                UserTeam directUserTeam = userTeamMapper.selectOne(new LambdaQueryWrapper<UserTeam>().eq(UserTeam::getDirectUserId, order.getUserId())); //查询下单人的团队
                Wallet directWallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId, directUserTeam.getTopUserId()));    //往团长钱包里面添加余额
                directWallet.setBalance(directWallet.getBalance().add(order.getAmount().multiply(BigDecimal.valueOf(0.2))));
                walletMapper.updateById(directWallet);
                User directUser = userMapper.selectById(directUserTeam.getDirectUserId());
                BalanceChange directBalanceChange = BalanceChange.builder().walletId(directWallet.getId())
                        .name("佣金-尾号" + directUser.getPhone().substring(directUser.getPhone().length() - 4) + "用户下单")
                        .amount(order.getAmount().multiply(BigDecimal.valueOf(0.2)))
                        .status(2).type(1).build();
                balanceChangeMapper.insert(directBalanceChange); //插入余额变动表记录
                // 收入分销(间接推荐人 提成10%)
                UserTeam topUserTeam = userTeamMapper.selectOne(new LambdaQueryWrapper<UserTeam>().eq(UserTeam::getDirectUserId, directUserTeam.getTopUserId())); //查出队长的团队
                Wallet topWallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId, topUserTeam.getTopUserId()));    //往钱包里面添加余额
                topWallet.setBalance(topWallet.getBalance().add(order.getAmount().multiply(BigDecimal.valueOf(0.1))));
                walletMapper.updateById(topWallet);
                BalanceChange topBalanceChange = BalanceChange.builder().walletId(topWallet.getId())
                        .name("佣金-尾号" + directUser.getPhone().substring(directUser.getPhone().length() - 4) + "用户下单")
                        .amount(order.getAmount().multiply(BigDecimal.valueOf(0.02)))
                        .status(2).type(1).build();
                balanceChangeMapper.insert(topBalanceChange); //插入余额变动表记录
                // 收入分销(城市代理商 提成10%)
                Agency agency = agencyMapper.selectOne(new LambdaQueryWrapper<Agency>().like(Agency::getArea, findAddressString(order.getAddress())));
                Wallet agencyWallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId, agency.getUserId()));    //往钱包里面添加余额
                agencyWallet.setBalance(agencyWallet.getBalance().add(order.getAmount().multiply(BigDecimal.valueOf(0.1))));
                walletMapper.updateById(agencyWallet);
                BalanceChange agencyBalanceChange = BalanceChange.builder().walletId(agencyWallet.getId())
                        .name("佣金-尾号" + directUser.getPhone().substring(directUser.getPhone().length() - 4) + "用户下单")
                        .amount(order.getAmount().multiply(BigDecimal.valueOf(0.1)))
                        .status(2).type(1).build();
                balanceChangeMapper.insert(agencyBalanceChange); //插入余额变动表记录
            } else if ("NOTPAY".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                order.setOrderStatus(OrderStatus.NO_PAY.getCode());
                order.setOrderStatusDesc(OrderStatus.NO_PAY.getStatus());
                //设置交易状态
                order.setTradeStatus(TradeStatus.NOT_PAY.getCode());
                order.setTradeStatusDesc(TradeStatus.NOT_PAY.getStatus());
                orderMapper.updateById(order);
            }
            return "支付成功";
        } catch (Exception ex) {
            log.error("支付通知-异常：{}", ex.getMessage());
            throw new ServiceException("支付通知-异常");
        }
    }

    //查询出地址里面市之前的字
    private String findAddressString(String address) {
        int index = address.indexOf("市");
        if (index != -1) {
            return address.substring(0, index);
        }
        return address;
    }

    @Override
    public ObituaryNoticeVO queryObituaryNoticeForGiftItems(Integer id) {
        ObituaryNotice obituaryNotice = obituaryNoticeMapper.selectById(id);
        if (obituaryNotice == null) {
            log.error("逝者讣告信息不存");
            throw new ServiceException("逝者讣告信息不存在");
        }
        //查询赠送给该讣告的礼物信息
        List<GiftItem> giftItems = giftItemMapper.selectList(new LambdaQueryWrapper<GiftItem>().eq(GiftItem::getObituaryId, id));
        ObituaryNoticeVO obituaryNoticeVO = new ObituaryNoticeVO();
        BeanUtils.copyProperties(obituaryNotice, obituaryNoticeVO);
        //将礼物信息存入讣告对象
        obituaryNoticeVO.setGiftItemList(giftItems);
        //统计每种礼物的数量
        Map<Integer, Long> giftItemCountMap = giftItems.stream().collect(Collectors.groupingBy(GiftItem::getItemId, Collectors.counting()));
        //设置每种礼物的数量
        if (!giftItemCountMap.isEmpty()) {
            for (Map.Entry<Integer, Long> entry : giftItemCountMap.entrySet()) {
                 switch (entry.getKey()) {
                     case 20:
                         obituaryNoticeVO.setLightMemorialCount(entry.getValue().intValue()); //追思灯数量
                         break;
                     case 21:
                         obituaryNoticeVO.setLightSafeCount(entry.getValue().intValue()); //平安灯数量
                         break;
                     case 22:
                         obituaryNoticeVO.setLightPrayCount(entry.getValue().intValue()); //祈福灯数量
                         break;
                     case 23:
                         obituaryNoticeVO.setLightFilialCount(entry.getValue().intValue()); //孝亲灯数量
                         break;
                 }
            }
        }
        return obituaryNoticeVO;
    }
}
