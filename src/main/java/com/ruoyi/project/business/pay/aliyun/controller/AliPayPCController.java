package com.ruoyi.project.business.pay.aliyun.controller;

import com.alipay.api.AlipayApiException;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.pay.aliyun.service.impl.AliPayPCService;
import com.ruoyi.project.business.pay.aliyun.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pay/alipay")
@Api(tags = "支付宝支付管理模块")
public class AliPayPCController {
    @Resource
    private AliPayPCService aliPayPCService;
    
    @PostMapping("/pc/pay")
    @ApiOperation("支付宝PC网页环境支付订单")
    public R<String> pay(@RequestBody AliPayTradePayVO aliPayTradePayVO) throws AlipayApiException {
        return R.ok(aliPayPCService.pay(aliPayTradePayVO));
    }
    
    @PostMapping("/pc/close")
    @ApiOperation("关闭支付宝PC网页环境支付订单")
    public R<String> close(@RequestBody AliPayTradeCloseVO aliPayTradeCloseVO) throws AlipayApiException {
        return R.ok(aliPayPCService.close(aliPayTradeCloseVO));
    }
    
    @PostMapping("/pc/query")
    @ApiOperation("查询支付宝PC网页环境支付订单")
    public R<String> query(@RequestBody AliPayTradeQueryVO aliPayTradeQueryVO) throws AlipayApiException {
        return R.ok(aliPayPCService.query(aliPayTradeQueryVO));
    }
    
    @PostMapping("/pc/refundQuery")
    @ApiOperation("查询支付宝PC网页环境的退款订单")
    public R<String> refundQuery(@RequestBody AliPayTradeRefundQueryVO aliPayTradeRefundQueryVO) throws AlipayApiException {
        return R.ok(aliPayPCService.refundQuery(aliPayTradeRefundQueryVO));
    }
    
    @PostMapping("/pc/refund")
    @ApiOperation("退款支付宝PC网页环境的订单")
    public R<String> refund(@RequestBody AliPayTradeRefundVO aliPayTradeRefundVO) throws AlipayApiException {
        return R.ok(aliPayPCService.refund(aliPayTradeRefundVO));
    }
    
    @PostMapping("/pc/queryDownloadBillUrl")
    @ApiOperation("查询支付宝PC网页环境的对账单下载地址")
    public R<String> queryDownloadBillUrl(@RequestBody AliPayDataBillDownloadVO aliPayDataBillDownloadVO) throws AlipayApiException {
        return R.ok(aliPayPCService.queryDownloadBillUrl(aliPayDataBillDownloadVO));
    }

    @RequestMapping("/pc/return")
    @ApiOperation("支付宝支付回调方法")
    public R<String> returnUrl(HttpServletRequest request, HttpServletResponse response) {
        return R.ok(aliPayPCService.returnUrl(request, response));
    }
}
