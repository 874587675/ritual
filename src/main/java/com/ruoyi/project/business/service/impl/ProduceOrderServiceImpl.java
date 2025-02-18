package com.ruoyi.project.business.service.impl;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.enums.TradeStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.project.business.constant.OrderConstant;
import com.ruoyi.project.business.domain.*;
import com.ruoyi.project.business.mapper.*;
import com.ruoyi.project.business.pay.wechat.common.WxPayCommon;
import com.ruoyi.project.business.pay.wechat.service.impl.WxPayJsapiService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.util.config.WxPayConfig;
import com.ruoyi.project.business.vo.GiftItemVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.service.ProduceOrderService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class ProduceOrderServiceImpl extends ServiceImpl<ProduceOrderMapper, ProduceOrder> implements ProduceOrderService {
    @Resource
    private ProduceOrderMapper produceOrderMapper;
    @Resource
    private RandomUtils randomUtils;
    @Resource
    private WxPayJsapiService wxPayJsapiService;
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private CouponUserMapper couponUserMapper;
    @Resource
    private WalletMapper walletMapper;
    @Resource
    private UserTeamMapper userTeamMapper;
    @Resource
    private BalanceChangeMapper balanceChangeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AgencyMapper agencyMapper;
    @Resource
    private SkuInfoMapper skuInfoMapper;
    @Resource
    private SpuInfoMapper spuInfoMapper;

    @Override
    public Map<String, String> createProduceOrder(WeChatJsapiPayVO weChatJsapiPayVO) {
        try {
            if (weChatJsapiPayVO == null) {
                throw new ServiceException("下单参数不能为空");
            }
            if (weChatJsapiPayVO.getProduceOrderVO().getPayType() == 1) {
                BigDecimal totalAmount = BigDecimal.valueOf(weChatJsapiPayVO.getAmount().getTotal());
                if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ServiceException("金额不能为空");
                }
                ProduceOrder produceOrder = ProduceOrder.builder()
                        .id(randomUtils.generateNumeric(11))
                        .userId(weChatJsapiPayVO.getProduceOrderVO().getUserId())
                        .openId(weChatJsapiPayVO.getPayer().getOpenid())
                        .orderNo("P" + randomUtils.generateNumeric(11))
                        .amount(totalAmount)
                        .subject(OrderConstant.PRODUCE_ORDER_ITEM)
                        .orderStatus(OrderStatus.NO_PAY.getCode())
                        .orderStatusDesc(OrderStatus.NO_PAY.getStatus())
                        .tradeStatus(TradeStatus.NOT_PAY.getCode())
                        .tradeStatusDesc(TradeStatus.NOT_PAY.getStatus())
                        .isDeleted(0)
                        .addressId(weChatJsapiPayVO.getProduceOrderVO().getAddressId())
                        .addressName(weChatJsapiPayVO.getProduceOrderVO().getAddressName())
                        .produceImage(weChatJsapiPayVO.getProduceOrderVO().getProduceImage())
                        .produceName(weChatJsapiPayVO.getProduceOrderVO().getProduceName())
                        .producePrice(weChatJsapiPayVO.getProduceOrderVO().getProducePrice())
                        .produceCount(weChatJsapiPayVO.getProduceOrderVO().getProduceCount())
                        .attributeName(weChatJsapiPayVO.getProduceOrderVO().getAttributeName())
                        .spuId(weChatJsapiPayVO.getProduceOrderVO().getSpuId())
                        .skuId(weChatJsapiPayVO.getProduceOrderVO().getSkuId())
                        .shippingFee(weChatJsapiPayVO.getProduceOrderVO().getShippingFee())
                        .couponId(weChatJsapiPayVO.getProduceOrderVO().getCouponId())
                        .couponName(weChatJsapiPayVO.getProduceOrderVO().getCouponName())
                        .couponDescrption(weChatJsapiPayVO.getProduceOrderVO().getCouponDescrption())
                        .finalPrice(weChatJsapiPayVO.getProduceOrderVO().getFinalPrice())
                        .note(weChatJsapiPayVO.getProduceOrderVO().getNote())
                        .payType(weChatJsapiPayVO.getProduceOrderVO().getPayType())
                        .payTypeName(weChatJsapiPayVO.getProduceOrderVO().getPayTypeName())
                        .logisticsInfo(weChatJsapiPayVO.getProduceOrderVO().getLogisticsInfo())
                        .build();
                //创建订单信息
                int result = produceOrderMapper.insert(produceOrder);
                if (result <= 0) {
                    throw new ServiceException("创建订单失败,请重试");
                } else {
                    //扣减对应sku库存数量
                    SkuInfo skuInfo = skuInfoMapper.selectById(weChatJsapiPayVO.getProduceOrderVO().getSkuId());
                    if (skuInfo == null) {
                        throw new ServiceException("查询不到该商品信息");
                    }
                    if (skuInfo.getStock() < weChatJsapiPayVO.getProduceOrderVO().getProduceCount()) {
                        throw new ServiceException("该分类商品的库存不足，请换一种分类");
                    }
                    int updateResult = skuInfoMapper.update(new LambdaUpdateWrapper<SkuInfo>().eq(SkuInfo::getId, skuInfo.getId()).set(SkuInfo::getStock, skuInfo.getStock() - 1).set(SkuInfo::getSaleCount, skuInfo.getSaleCount() + 1));
                    if (updateResult == 0){
                        log.info("该商品库存失败");
                    }
                    // 更新优惠券状态
                    CouponUser couponUser = couponUserMapper.selectById(produceOrder.getCouponId());
                    if (couponUser != null) {
                        couponUser.setStatus(2);
                        couponUser.setUsedTime(new Date());
                        int couponResult = couponUserMapper.updateById(couponUser);
                        if (couponResult <= 0) {
                            log.error("更新优惠券状态失败");
                            throw new ServiceException("更新优惠券状态失败");
                        }
                        log.info("更新优惠券状态成功");
                    }
                    //设置商户订单号
                    weChatJsapiPayVO.setOutTradeNo(produceOrder.getOrderNo());
                    //设置商品订单描述信息
                    weChatJsapiPayVO.setDescription(produceOrder.getSubject());
                    log.info("创建订单成功：{}", produceOrder);
                    return wxPayJsapiService.wxPay(weChatJsapiPayVO);
                }
            }
            if (weChatJsapiPayVO.getProduceOrderVO().getPayType() == 2) {
                Wallet wallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getOpenId, weChatJsapiPayVO.getPayer().getOpenid()));
                if (wallet == null) {
                    log.error("用户钱包不存在，openId:{}", weChatJsapiPayVO.getPayer().getOpenid());
                    throw new ServiceException("用户钱包不存在");
                }
                if (wallet.getBalance().compareTo(weChatJsapiPayVO.getProduceOrderVO().getAmount()) < 0) {
                    log.error("用户余额不足，openId:{}", weChatJsapiPayVO.getPayer().getOpenid());
                    throw new ServiceException("用户余额不足");
                }
                wallet.setBalance(wallet.getBalance().subtract(weChatJsapiPayVO.getProduceOrderVO().getAmount()));
                int updateResult = walletMapper.updateById(wallet);
                if (updateResult <= 0) {
                    log.error("扣除用户余额失败，openId:{}", weChatJsapiPayVO.getPayer().getOpenid());
                    throw new ServiceException("扣除用户余额失败");
                }
                log.info("扣除用户余额成功，openId:{}", weChatJsapiPayVO.getPayer().getOpenid());
            }
        } catch (Exception e) {
            log.error("创建订单失败：{}", e.getMessage());
            throw new ServiceException("创建订单失败,请重试");
        }
        return null;
    }

    @Override
    @Transactional
    public String payProduceCallback(HttpServletRequest request) {
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
            ProduceOrder produceOrder = produceOrderMapper.selectOne(new LambdaQueryWrapper<ProduceOrder>()
                    .eq(ProduceOrder::getOrderNo, transaction.getOutTradeNo())
                    .eq(ProduceOrder::getIsDeleted, 0));
            // 2、更新订单状态
            if ("SUCCESS".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                produceOrder.setOrderStatus(OrderStatus.WAIT_SHIP.getCode());
                produceOrder.setOrderStatusDesc(OrderStatus.WAIT_SHIP.getStatus());
                //设置交易状态
                produceOrder.setTradeStatus(TradeStatus.SUCCESS.getCode());
                produceOrder.setTradeStatusDesc(TradeStatus.SUCCESS.getStatus());
                produceOrderMapper.updateById(produceOrder);
                // 收入分佣（直接推荐人(队长收入 抽成3%)）
                UserTeam directUserTeam = userTeamMapper.selectOne(new LambdaQueryWrapper<UserTeam>().eq(UserTeam::getDirectUserId, produceOrder.getUserId())); //查询下单人的团队
                Wallet directWallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId, directUserTeam.getTopUserId()));    //往团长钱包里面添加余额
                directWallet.setBalance(directWallet.getBalance().add(produceOrder.getAmount().multiply(BigDecimal.valueOf(0.03))));
                walletMapper.updateById(directWallet);
                User directUser = userMapper.selectById(directUserTeam.getDirectUserId());
                BalanceChange directBalanceChange = BalanceChange.builder().walletId(directWallet.getId())
                        .name("佣金-尾号" + directUser.getPhone().substring(directUser.getPhone().length() - 4) + "用户下单")
                        .amount(produceOrder.getAmount().multiply(BigDecimal.valueOf(0.03)))
                        .status(2).type(1).build();
                balanceChangeMapper.insert(directBalanceChange); //插入余额变动表记录
                // 收入分佣（间接推荐人(团长收入 抽成2%)）
                UserTeam topUserTeam = userTeamMapper.selectOne(new LambdaQueryWrapper<UserTeam>().eq(UserTeam::getDirectUserId, directUserTeam.getTopUserId())); //查出队长的团队
                Wallet topWallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId, topUserTeam.getTopUserId()));    //往钱包里面添加余额
                topWallet.setBalance(topWallet.getBalance().add(produceOrder.getAmount().multiply(BigDecimal.valueOf(0.02))));
                walletMapper.updateById(topWallet);
                BalanceChange topBalanceChange = BalanceChange.builder().walletId(topWallet.getId())
                        .name("佣金-尾号" + directUser.getPhone().substring(directUser.getPhone().length() - 4) + "用户下单")
                        .amount(produceOrder.getAmount().multiply(BigDecimal.valueOf(0.02)))
                        .status(2).type(1).build();
                balanceChangeMapper.insert(topBalanceChange); //插入余额变动表记录
                // 收入分佣（城市代理商 抽成5%）
                Agency agency = agencyMapper.selectOne(new LambdaQueryWrapper<Agency>().like(Agency::getArea, findAddressString(produceOrder.getAddressName())));
                Wallet agencyWallet = walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId, agency.getUserId()));    //往钱包里面添加余额
                agencyWallet.setBalance(agencyWallet.getBalance().add(produceOrder.getAmount().multiply(BigDecimal.valueOf(0.05))));
                walletMapper.updateById(agencyWallet);
                BalanceChange agencyBalanceChange = BalanceChange.builder().walletId(agencyWallet.getId())
                        .name("佣金-尾号" + directUser.getPhone().substring(directUser.getPhone().length() - 4) + "用户下单")
                        .amount(produceOrder.getAmount().multiply(BigDecimal.valueOf(0.05)))
                        .status(2).type(1).build();
                balanceChangeMapper.insert(agencyBalanceChange); //插入余额变动表记录
            } else if ("NOTPAY".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                produceOrder.setOrderStatus(OrderStatus.NO_PAY.getCode());
                produceOrder.setOrderStatusDesc(OrderStatus.NO_PAY.getStatus());
                //设置交易状态
                produceOrder.setTradeStatus(TradeStatus.NOT_PAY.getCode());
                produceOrder.setTradeStatusDesc(TradeStatus.NOT_PAY.getStatus());
                produceOrderMapper.updateById(produceOrder);
            }
            return "支付成功";
        } catch (Exception e) {
            log.error("支付通知-异常：{}", e.getMessage());
            throw new ServiceException("支付回调处理失败");
        }
    }

    @Override
    @Transactional
    public String closeProduceOrder(Integer id) {
        try {
            ProduceOrder produceOrder = produceOrderMapper.selectById(id);
            if (produceOrder == null) {
                log.info("不存在此订单，订单号：{}", id);
                throw new ServiceException("订单不存在");
            }
            if (OrderStatus.NO_PAY.getCode().equals(produceOrder.getOrderStatus())) {
                produceOrder.setOrderStatus(OrderStatus.CLOSED.getCode());
                produceOrder.setOrderStatusDesc(OrderStatus.CLOSED.getStatus());
                produceOrder.setTradeStatus(TradeStatus.CLOSED.getCode());
                produceOrder.setTradeStatusDesc(TradeStatus.CLOSED.getStatus());
                int closeResult = produceOrderMapper.updateById(produceOrder);

                // 更新优惠券状态
                CouponUser couponUser = couponUserMapper.selectById(produceOrder.getCouponId());
                if (couponUser != null) {
                    couponUser.setStatus(1);
                    couponUser.setUsedTime(new Date());
                    couponUserMapper.updateById(couponUser);
                    log.info("更新优惠券状态成功");
                }
                if (closeResult <= 0) {
                    log.error("关闭订单失败，订单号：{}", id);
                    throw new ServiceException("关闭订单失败");
                }
                return "关闭订单成功";
            }

            if (OrderStatus.PAYED.getCode().equals(produceOrder.getOrderStatus())) {
                log.info("此订单已支付，无法关闭，订单号：{}", id);
                throw new ServiceException("此订单已支付，无法关闭");
            }
            return "关闭订单失败";
        } catch (Exception e) {
            log.error("关闭订单失败：{}", e.getMessage());
            throw new ServiceException("关闭订单失败");
        }
    }

    @Override
    @Transactional
    public String confirmProduceOrder(Integer id) {
        try {
            ProduceOrder produceOrder = produceOrderMapper.selectById(id);
            if (produceOrder == null) {
                log.info("不存在此订单，订单号：{}", id);
                throw new ServiceException("订单不存在");
            }
            if (OrderStatus.WAIT_RECEIVE.getCode().equals(produceOrder.getOrderStatus())) {
                produceOrder.setOrderStatus(OrderStatus.WAIT_EVALUATE.getCode());
                produceOrder.setOrderStatusDesc(OrderStatus.WAIT_EVALUATE.getStatus());
                int confirmResult = produceOrderMapper.updateById(produceOrder);
                if (confirmResult <= 0) {
                    log.error("确认收货失败，订单号：{}", id);
                    throw new ServiceException("确认收货失败");
                }
                return "确认收货成功";
            }
            return "确认收货失败";
        } catch (Exception e) {
            log.error("确认收货失败：{}", e.getMessage());
            throw new ServiceException("确认收货失败");
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
}
