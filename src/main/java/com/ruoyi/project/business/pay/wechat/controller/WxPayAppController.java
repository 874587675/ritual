package com.ruoyi.project.business.pay.wechat.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.pay.wechat.service.impl.WxPayAppService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatAppPayVO;
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
public class WxPayAppController {
    @Resource
    private WxPayAppService wxPayAppService;

    @PostMapping("/app/wxPay")
    @ApiOperation("微信App环境支付订单")
    public R<Map<String, String>> wxPay(@RequestBody WeChatAppPayVO weChatAppPayVO) throws Exception {
        return R.ok(wxPayAppService.wxPay(weChatAppPayVO));
    }

    @GetMapping("/app/queryOrderByTransactionId")
    @ApiOperation("通过微信订单交易号查询App订单")
    public R<Transaction> queryOrderByTransactionId(@RequestParam String transactionId) throws Exception {
        return R.ok(wxPayAppService.queryOrderByTransactionId(transactionId));
    }

    @GetMapping("/app/queryOrderByOutTradeNo")
    @ApiOperation("通过商户订单号查询App订单")
    public R<Transaction> queryOrderByOutTradeNo(@RequestParam String outTradeNo) throws Exception {
        return R.ok(wxPayAppService.queryOrderByOutTradeNo(outTradeNo));
    }

    @GetMapping("/app/closeByOutTradeNo")
    @ApiOperation("通过商户订单号关闭微信App订单")
    public R<Boolean> closeByOutTradeNo(@RequestParam String outTradeNo) {
        return R.ok(wxPayAppService.closeByOutTradeNo(outTradeNo));
    }

    @GetMapping("/app/refundsByOutTradeNo")
    @ApiOperation("商户订单号App申请退款")
    public R<Boolean> refundsByOutTradeNo(@RequestParam String outTradeNo,
                                          @RequestParam String outRefundNo,
                                          @RequestParam Integer total,
                                          @RequestParam Integer refund){
        return R.ok(wxPayAppService.refundsByOutTradeNo(outTradeNo,outRefundNo,total,refund));
    }

    @GetMapping("/app/refundsByTransactionId")
    @ApiOperation("微信交易订单号App申请退款")
    public R<Boolean> refundsByTransactionId(@RequestParam String transactionId,
                                             @RequestParam String outRefundNo,
                                             @RequestParam Integer total,
                                             @RequestParam Integer refund){
        return R.ok(wxPayAppService.refundsByTransactionId(transactionId,outRefundNo,total,refund));
    }
    @GetMapping("/app/refundsQueryByOutRefundNo")
    @ApiOperation("查询微信App退款单")
    public R<Refund> refundsQueryByOutRefundNo(@RequestParam String outRefundNo) {
        return R.ok(wxPayAppService.refundsQueryByOutRefundNo(outRefundNo));
    }

    @PostMapping("/app/notify/wxPay")
    @ApiOperation("微信支付回调App通知")
    public R<Transaction> notify(HttpServletRequest request) {
        return R.ok(wxPayAppService.notify(request));
    }

    @PostMapping("/app/refund/wxPay")
    @ApiOperation("微信退款回调App通知")
    public R<RefundNotification> refundNotify(HttpServletRequest request) {
        return R.ok(wxPayAppService.refundNotify(request));
    }
}
