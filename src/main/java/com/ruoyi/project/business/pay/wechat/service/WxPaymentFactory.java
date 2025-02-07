package com.ruoyi.project.business.pay.wechat.service;


import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;

import javax.servlet.http.HttpServletRequest;

public interface WxPaymentFactory {
    Transaction queryOrderByTransactionId(String transactionId) throws Exception; //根据微信支付订单号查询

    Transaction queryOrderByOutTradeNo(String outTradeNo) throws Exception; //根据微信支付订单号查询

    Boolean closeByOutTradeNo(String outTradeNo);

    Boolean refundsByOutTradeNo(String outTradeNo,String outRefundNo,Integer total, Integer refund);

    Boolean refundsByTransactionId(String transactionId,String outRefundNo,Integer total, Integer refund);

    Refund refundsQueryByOutRefundNo(String outRefundNo);

    Transaction notify(HttpServletRequest request);

    RefundNotification refundNotify(HttpServletRequest request);
}
