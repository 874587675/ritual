package com.ruoyi.project.business.pay.aliyun.service;

import com.alipay.api.AlipayApiException;
import com.ruoyi.project.business.pay.aliyun.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @program: RuoYi-Vue-fast
 * @ClassName AliPayment
 * @description:
 * @author: zgc
 * @date: 2025-01-15 10:03
 * @Version 1.0
 **/
public interface AliPayment {
    String pay(AliPayTradePayVO aliPayTradePayVO) throws AlipayApiException;
    
    String close(AliPayTradeCloseVO aliPayTradeCloseVO) throws AlipayApiException;
    
    String query(AliPayTradeQueryVO aliPayTradeQueryVO) throws AlipayApiException;
    
    String refund(AliPayTradeRefundVO aliPayTradeRefundVO) throws AlipayApiException;
    
    String refundQuery(AliPayTradeRefundQueryVO aliPayTradeRefundQueryVO) throws AlipayApiException;
    
    String queryDownloadBillUrl(AliPayDataBillDownloadVO aliPayDataBillDownloadVO) throws AlipayApiException;
    
    String returnUrl(HttpServletRequest request, HttpServletResponse response);
}
