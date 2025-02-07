package com.ruoyi.project.business.pay.wechat.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.pay.wechat.service.impl.WxPayJsapiService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
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
public class WxPayJsapiController {
    @Resource
    private WxPayJsapiService wxPayJsapiService;
    
    @PostMapping("/jsapi/wxPay")
    @ApiOperation("微信JSAPI环境支付订单")
    public R<Map<String, String>> wxPay(@RequestBody WeChatJsapiPayVO WeChatJsapiPayVO) throws Exception {
        return R.ok(wxPayJsapiService.wxPay(WeChatJsapiPayVO));
    }
    
    @GetMapping("/jsapi/queryOrderByTransactionId")
    @ApiOperation("通过微信订单交易号查询JSAPI订单")
    public R<Transaction> queryOrderByTransactionId(@RequestParam String transactionId) throws Exception {
        return R.ok(wxPayJsapiService.queryOrderByTransactionId(transactionId));
    }

    @GetMapping("/jsapi/queryOrderByOutTradeNo")
    @ApiOperation("通过商户订单号查询JSAPI订单")
    public R<Transaction> queryOrderByOutTradeNo(@RequestParam String outTradeNo) throws Exception {
        return R.ok(wxPayJsapiService.queryOrderByOutTradeNo(outTradeNo));
    }
    
    @GetMapping("/jsapi/closeByOutTradeNo")
    @ApiOperation("通过商户订单号关闭微信JSAPI订单")
    public R<Boolean> closeByOutTradeNo(@RequestParam String outTradeNo) {
        return R.ok(wxPayJsapiService.closeByOutTradeNo(outTradeNo));
    }
    
    @GetMapping("/jsapi/refundsByOutTradeNo")
    @ApiOperation("商户订单号JSAPI申请退款")
    public R<Boolean> refundsByOutTradeNo(@RequestParam String outTradeNo,
                                              @RequestParam String outRefundNo,
                                              @RequestParam Integer total, 
                                              @RequestParam Integer refund){
        return R.ok(wxPayJsapiService.refundsByOutTradeNo(outTradeNo,outRefundNo,total,refund));
    }
    
    @GetMapping("/jsapi/refundsByTransactionId")
    @ApiOperation("微信交易订单号JSAPI申请退款")
    public R<Boolean> refundsByTransactionId(@RequestParam String transactionId,
                                                 @RequestParam String outRefundNo,
                                                 @RequestParam Integer total,
                                                 @RequestParam Integer refund){
        return R.ok(wxPayJsapiService.refundsByTransactionId(transactionId,outRefundNo,total,refund));
    }
    @GetMapping("/jsapi/refundsQueryByOutRefundNo")
    @ApiOperation("查询微信JSAPI退款单")
    public R<Refund> refundsQueryByOutRefundNo(@RequestParam String outRefundNo) {
        return R.ok(wxPayJsapiService.refundsQueryByOutRefundNo(outRefundNo));
    }
    
    @PostMapping("/jsapi/notify/wxPay")
    @ApiOperation("微信支付回调JSAPI通知")
    public R<Transaction> notify(HttpServletRequest request) {
        return R.ok(wxPayJsapiService.notify(request));
    }
    
    @PostMapping("/notify/refund/wxPay")
    @ApiOperation("微信退款回调JSAPI通知")
    public R<RefundNotification> refundNotify(HttpServletRequest request) {
        return R.ok(wxPayJsapiService.refundNotify(request));
    }
    
}
