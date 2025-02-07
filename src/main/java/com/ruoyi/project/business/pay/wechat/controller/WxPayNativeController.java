package com.ruoyi.project.business.pay.wechat.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.pay.wechat.service.impl.WxpayNativeService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatNativePayVO;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/pay/wxpay")
@Api(tags = "微信支付管理模块")
public class WxPayNativeController {
    @Resource
    private WxpayNativeService wxpayNativeService;

    @PostMapping("/native/wxPay")
    @ApiOperation("微信Native环境支付订单")
    public R<Map<String, String>> wxPay(@RequestBody WeChatNativePayVO weChatNativePayVO) throws Exception {
        return R.ok(wxpayNativeService.wxPay(weChatNativePayVO));
    }

    @GetMapping("/native/queryOrderByTransactionId")
    @ApiOperation("通过微信订单交易号查询Native订单")
    public R<Transaction> queryOrderByTransactionId(@RequestParam String transactionId) throws Exception {
        return R.ok(wxpayNativeService.queryOrderByTransactionId(transactionId));
    }

    @GetMapping("/native/queryOrderByOutTradeNo")
    @ApiOperation("通过商户订单号查询Native订单")
    public R<Transaction> queryOrderByOutTradeNo(@RequestParam String outTradeNo) throws Exception {
        return R.ok(wxpayNativeService.queryOrderByOutTradeNo(outTradeNo));
    }

    @GetMapping("/native/closeByOutTradeNo")
    @ApiOperation("通过商户订单号关闭微信Native订单")
    public R<Boolean> closeByOutTradeNo(@RequestParam String outTradeNo) {
        return R.ok(wxpayNativeService.closeByOutTradeNo(outTradeNo));
    }

    @GetMapping("/native/refundsByOutTradeNo")
    @ApiOperation("商户订单号Native申请退款")
    public R<Boolean> refundsByOutTradeNo(@RequestParam String outTradeNo,
                                          @RequestParam String outRefundNo,
                                          @RequestParam Integer total,
                                          @RequestParam Integer refund){
        return R.ok(wxpayNativeService.refundsByOutTradeNo(outTradeNo,outRefundNo,total,refund));
    }

    @GetMapping("/native/refundsByTransactionId")
    @ApiOperation("微信交易订单号Native申请退款")
    public R<Boolean> refundsByTransactionId(@RequestParam String transactionId,
                                             @RequestParam String outRefundNo,
                                             @RequestParam Integer total,
                                             @RequestParam Integer refund){
        return R.ok(wxpayNativeService.refundsByTransactionId(transactionId,outRefundNo,total,refund));
    }
    @GetMapping("/native/refundsQueryByOutRefundNo")
    @ApiOperation("查询微信Native退款单")
    public R<Refund> refundsQueryByOutRefundNo(@RequestParam String outRefundNo) {
        return R.ok(wxpayNativeService.refundsQueryByOutRefundNo(outRefundNo));
    }

    @PostMapping("/native/notify/wxPay")
    @ApiOperation("微信支付回调Native通知")
    public R<Transaction> notify(HttpServletRequest request) {
        return R.ok(wxpayNativeService.notify(request));
    }

    @PostMapping("/native/refund/wxPay")
    @ApiOperation("微信退款回调Native通知")
    public R<RefundNotification> refundNotify(HttpServletRequest request) {
        return R.ok(wxpayNativeService.refundNotify(request));
    }
}
