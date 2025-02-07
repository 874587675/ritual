package com.ruoyi.project.business.pay.wechat.service.impl;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.business.pay.wechat.common.WxPayCommon;
import com.ruoyi.project.business.pay.wechat.service.WxPaymentFactory;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.util.config.WxPayConfig;
import com.ruoyi.project.business.util.wechat.WxPayUtil;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: WxPayJsapiService
 * @description: 微信支付jsapi支付
 * @author: zgc
 **/
@Service
@Slf4j
public class WxPayJsapiService implements WxPaymentFactory {
    @Resource
    private WxPayConfig wxPayConfig;
    @Resource
    private WxPayUtil wxPayUtil;
    @Resource
    private RedisCache redisCache;
    
    /**
     * 调用微信写好的
     * @param weChatJsapiPayVO
     * @return
     * @throws Exception
     */
    public Map<String, String> wxPay(WeChatJsapiPayVO weChatJsapiPayVO) throws Exception {
//        String openId = "ou6YH41g7ceN1XECAoVaCgKeYAco";
        Map<String, String> oldPayReturnMap = redisCache.getCacheObject(WxPayCommon.getWxPayCacheKey(weChatJsapiPayVO));
        if (oldPayReturnMap != null) {
            return oldPayReturnMap;
        }
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        //构建请求
        PrepayRequest request = new PrepayRequest();
        //基础信息
        request.setAppid(wxPayConfig.getPayparams().getAppId());
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setDescription(weChatJsapiPayVO.getDescription());     //商品信息描述
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());    // 设置回调地址
        request.setOutTradeNo(weChatJsapiPayVO.getOutTradeNo());
        
        //订单金额信息
        Amount amount = new Amount();
        amount.setTotal(1);
        request.setAmount(amount);
        
        //用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid

        Payer payer = new Payer();
        payer.setOpenid(weChatJsapiPayVO.getPayer().getOpenid());
        request.setPayer(payer);
        try {
            PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(request);
            log.info("JSAPI下单-结果：{}", JSON.toJSONString(response));
            Map<String, String> payReturnMap = wxPayUtil.getPayReturnMap(response);
            // 将支付信息存入数据库
            redisCache.setCacheObject(WxPayCommon.getWxPayCacheKey(weChatJsapiPayVO), payReturnMap);
            return payReturnMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("JSAPI下单-失败");
        }
    }

    @Override
    public Transaction queryOrderByTransactionId(String transactionId) throws Exception {
        // 构建service
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        QueryOrderByIdRequest request = new QueryOrderByIdRequest();
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setTransactionId(transactionId);
        try {
            Transaction transaction = service.queryOrderById(request);
            log.info("查询订单-微信支付微信订单号查询-结束：{}",JSON.toJSONString(transaction));
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("查询订单-微信支付微信订单号查询-失败");
        }
    }

    @Override
    public Transaction queryOrderByOutTradeNo(String outTradeNo) throws Exception {
        // 构建service
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setOutTradeNo(outTradeNo);
        try {
            Transaction transaction = service.queryOrderByOutTradeNo(request);
            log.info("查询订单-微信支付商户订单号查询-结束：{}",JSON.toJSONString(transaction));
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("查询订单-微信商户支付订单号查询-失败");
        }
    }

    @Override
    public Boolean closeByOutTradeNo(String outTradeNo) {
        log.info("关闭订单-开始");
        // 构建service
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
        //构建请求
        CloseOrderRequest request = new CloseOrderRequest();
        request.setMchid(wxPayConfig.getPayparams().getMerchantId());
        request.setOutTradeNo(outTradeNo);
        try {
            service.closeOrder(request);
            log.info("关闭订单-结束：{}", true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("关闭订单-失败");
        }
    }

    @Override
    public Boolean refundsByOutTradeNo(String outTradeNo,String outRefundNo,Integer total, Integer refund) {
        log.info("申请退款-开始");
        // 构建service
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        RefundService service = new RefundService.Builder().config(config).build();
        //构建请求
        CreateRequest request = new CreateRequest();
        request.setOutTradeNo(outTradeNo);
        request.setOutRefundNo(outRefundNo);
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());
        //金额信息
        AmountReq amountReq = new AmountReq();
        amountReq.setRefund(refund + 0l);   
        amountReq.setTotal(total + 0l);
        amountReq.setCurrency("CNY");
        request.setAmount(amountReq);
        try {
            service.create(request);
            log.info("申请退款-结束：{}",true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("申请退款-异常");
        }
    }

    @Override
    public Boolean refundsByTransactionId(String transactionId,String outRefundNo,Integer total, Integer refund) {
        log.info("申请退款-开始");
        // 构建service
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        RefundService service = new RefundService.Builder().config(config).build();
        //构建请求
        CreateRequest request = new CreateRequest();
        request.setTransactionId(transactionId);
        request.setOutRefundNo(outRefundNo);
        request.setNotifyUrl(wxPayConfig.getPayparams().getNotifyUrl());
        //金额信息
        AmountReq amountReq = new AmountReq();
        amountReq.setRefund(refund + 0l);
        amountReq.setTotal(total + 0l);
        amountReq.setCurrency("CNY");
        request.setAmount(amountReq);
        try {
            service.create(request);
            log.info("申请退款-结束：{}", true);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("申请退款-异常");
        }
    }

    @Override
    public Refund refundsQueryByOutRefundNo(String outRefundNo) {
        log.info("查询单笔退款-开始");
        // 构建service
        RSAAutoCertificateConfig config = wxPayConfig.getWxPayConfig();
        RefundService service = new RefundService.Builder().config(config).build();
        //构建请求
        QueryByOutRefundNoRequest request = new QueryByOutRefundNoRequest();
        request.setOutRefundNo(outRefundNo);
        try {
            Refund transaction = service.queryByOutRefundNo(request);
            log.info("查询单笔退款-结束：{}",JSON.toJSONString(transaction));
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("查询单笔退款-异常");
        }
    }

    @Override
    public Transaction notify(HttpServletRequest request) {
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
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("支付通知-异常");
        }
    }

    @Override
    public RefundNotification refundNotify(HttpServletRequest request) {
        log.info("退款结果通知-开始");
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
            RefundNotification refundNotification = parser.parse(requestParam, RefundNotification.class);
            log.info("退款结果通知-结束：{}", JSON.toJSONString(refundNotification));
            return refundNotification;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceException("退款结果通知-异常");
        }
    }
}
