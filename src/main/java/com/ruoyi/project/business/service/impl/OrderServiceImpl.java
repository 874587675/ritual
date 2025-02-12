package com.ruoyi.project.business.service.impl;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.enums.TradeStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.random.RandomUtils;
import com.ruoyi.framework.redis.ZSetComponent;
import com.ruoyi.project.business.constant.OrderConstant;
import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.domain.OrderSuccess;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.GiftItemMapper;
import com.ruoyi.project.business.mapper.OrderMapper;
import com.ruoyi.project.business.mapper.OrderSuccessMapper;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.pay.wechat.common.WxPayCommon;
import com.ruoyi.project.business.pay.wechat.service.impl.WxPayJsapiService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.service.OrderService;
import com.ruoyi.project.business.util.config.WxPayConfig;
import com.ruoyi.project.business.vo.GiftItemVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderSuccessMapper orderSuccessMapper;
    @Resource
    private RandomUtils randomUtils;
    @Resource
    private WxPayJsapiService wxPayJsapiService;
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private GiftItemMapper giftItemMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ZSetComponent zSetComponent;

    @Override
    public Map<String, String> createItemOrder(WeChatJsapiPayVO WeChatJsapiPayVO) {
        try {
            if (WeChatJsapiPayVO == null) {
                throw new ServiceException("下单参数不能为空");
            }
            BigDecimal totalAmount = BigDecimal.valueOf(WeChatJsapiPayVO.getAmount().getTotal());
            if (totalAmount.compareTo(BigDecimal.ZERO) == 0) {
                throw new ServiceException("金额不能为空");
            }
            Order order = Order.builder()
                    .id(randomUtils.generateNumeric(11))
                    .openId(WeChatJsapiPayVO.getPayer().getOpenid())
                    .orderNo("I" + randomUtils.generateNumeric(11))
                    .amount(totalAmount)
                    .subject(OrderConstant.ORDER_ITEM)
                    .orderStatus(OrderStatus.NO_PAY.getCode())
                    .orderStatusDesc(OrderStatus.NO_PAY.getStatus())
                    .build();
            //创建订单信息
            int result = orderMapper.insert(order);
            
            //创建供奉礼物的信息
            GiftItemVO giftItemVO = WeChatJsapiPayVO.getGiftItemVO();
            User user = userMapper.selectById(giftItemVO.getUserId());

            // 获取当前时间
            Calendar calendar = Calendar.getInstance();
            // 当前时间加上有效天数
            calendar.add(Calendar.DAY_OF_YEAR, giftItemVO.getValidateDays());
            GiftItem giftItem = GiftItem.builder()
                    .itemId(giftItemVO.getItemId())
                    .userId(giftItemVO.getUserId())
                    .nickName(user.getNickname())
                    .count(giftItemVO.getCount())
                    .roomId(giftItemVO.getRoomId())
                    .score(giftItemVO.getScore())
                    .museumId(giftItemVO.getMuseumId())
                    .orderId(order.getId())
                    .orderStatus(OrderStatus.NO_PAY.getCode())
                    .expireTime(calendar.getTime())
                    .build();
            int giftResult = giftItemMapper.insert(giftItem);
            
            if (result <= 0 || giftResult <= 0) {
                throw new ServiceException("创建订单失败,请重试");
            } else {
                //设置商户订单号
                WeChatJsapiPayVO.setOutTradeNo(order.getOrderNo());
                //设置商品订单描述信息
                WeChatJsapiPayVO.setDescription(order.getSubject());
                log.info("创建订单成功：{}", order);
                return wxPayJsapiService.wxPay(WeChatJsapiPayVO);
            }
        } catch (Exception e) {
            log.error("创建订单失败：{}", e.getMessage());
            throw new ServiceException("创建订单失败,请重试");
        }
    }

    @Override
    @Transactional
    public String payItemCallback(HttpServletRequest request) {
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
            if (order == null) {
                throw new ServiceException("订单不存在");
            }
            // 2、更新订单状态
            if ("SUCCESS".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                order.setOrderStatus(OrderStatus.PAYED.getCode());
                order.setOrderStatusDesc(OrderStatus.PAYED.getStatus());
                //设置交易状态
                order.setTradeStatus(TradeStatus.SUCCESS.getCode());
                order.setTradeStatusDesc(TradeStatus.SUCCESS.getStatus());
                int payResult = orderMapper.updateById(order);
                if (payResult <= 0) {
                    log.error("更新支付记录失败");
                    throw new ServiceException("更新支付记录失败");
                }
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
                int orderSuccessResult = orderSuccessMapper.insert(orderSuccess);
                
                //更新供奉礼物的信息
                GiftItem giftItem = giftItemMapper.selectOne(new LambdaQueryWrapper<GiftItem>()
                        .eq(GiftItem::getOrderId, order.getId()));
                giftItem.setOrderStatus(OrderStatus.PAYED.getCode());
                int giftResult = giftItemMapper.updateById(giftItem);
                
                //更新用户积分(用户在当前房间的排行和用户在总的排行)
                zSetComponent.sendGift(giftItem.getUserId(), giftItem.getRoomId(), giftItem.getScore());
                zSetComponent.sendGift(giftItem.getUserId(), giftItem.getScore());
                
                if (orderSuccessResult <= 0 || giftResult <= 0) {
                    log.error("支付成功订单记录失败");
                    throw new ServiceException("支付成功订单记录失败");
                }
            } else if ("CLOSED".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                order.setOrderStatus(OrderStatus.CLOSED.getCode());
                order.setOrderStatusDesc(OrderStatus.CLOSED.getStatus());
                //设置交易状态
                order.setTradeStatus(TradeStatus.CLOSED.getCode());
                order.setTradeStatusDesc(TradeStatus.CLOSED.getStatus());
                int payResult = orderMapper.updateById(order);
                if (payResult <= 0) {
                    log.error("关闭支付订单状态失败");
                    throw new ServiceException("关闭支付订单状态失败");
                }
            } else if ("NOTPAY".equalsIgnoreCase(transaction.getTradeState().toString())) {
                //设置订单状态
                order.setOrderStatus(OrderStatus.NO_PAY.getCode());
                order.setOrderStatusDesc(OrderStatus.NO_PAY.getStatus());
                //设置交易状态
                order.setTradeStatus(TradeStatus.NOT_PAY.getCode());
                order.setTradeStatusDesc(TradeStatus.NOT_PAY.getStatus());
                int payResult = orderMapper.updateById(order);
                if (payResult <= 0) {
                    log.error("修改未支付订单状态失败");
                    throw new ServiceException("修改未支付订单状态失败");
                }
            }
            return "支付成功";
        } catch (Exception ex) {
            log.error("支付通知-异常：{}", ex.getMessage());
            throw new ServiceException("支付通知-异常");
        }
    }
}
