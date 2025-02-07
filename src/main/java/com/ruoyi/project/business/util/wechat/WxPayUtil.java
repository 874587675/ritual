package com.ruoyi.project.business.util.wechat;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * @program: RuoYi-Vue-fast
 * @ClassName WxPayUtil
 * @description:
 * @author: zgc
 * @date: 2025-01-17 01:35
 * @Version 1.0
 **/
@Component
public class WxPayUtil {
    public  Map<String, String> getPayReturnMap(Object response) throws Exception {
        // 构建JSAPI支付参数
        if (response instanceof com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse){
            com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse jsapiResponse =
                    (com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("appId", jsapiResponse.getAppId());
            payParams.put("timeStamp", jsapiResponse.getTimeStamp());
            payParams.put("nonceStr", jsapiResponse.getNonceStr());
            payParams.put("package", "prepay_id=" + jsapiResponse.getPackageVal());
            payParams.put("signType", jsapiResponse.getSignType());
            payParams.put("paySign", jsapiResponse.getPaySign());            //生成签名
            return payParams;           // 返回支付参数
        }
        //构建APP支付参数
        else if (response instanceof com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse) {
            com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse appResponse =
                    (com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("appId", appResponse.getAppid());
            payParams.put("partnerId",appResponse.getPartnerId());
            payParams.put("prepayId", appResponse.getPrepayId());
            payParams.put("timeStamp", appResponse.getTimestamp());
            payParams.put("nonceStr", appResponse.getNonceStr());
            payParams.put("packageValue", appResponse.getPackageVal());
            payParams.put("sign", appResponse.getSign());
            return payParams;           // 返回支付参数
        }
        else if (response instanceof com.wechat.pay.java.service.payments.h5.model.PrepayResponse){
            com.wechat.pay.java.service.payments.h5.model.PrepayResponse h5Response =
                    (com.wechat.pay.java.service.payments.h5.model.PrepayResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("h5_url", h5Response.getH5Url());
            return payParams;           // 返回支付参数
        }
        else if (response instanceof com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse){
            com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse nativeResponse =
                    (com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse) response;
            Map<String, String> payParams = new TreeMap<>();
            payParams.put("code_url", nativeResponse.getCodeUrl());
            return payParams;           // 返回支付参数
        }

        return null;
    }
}
