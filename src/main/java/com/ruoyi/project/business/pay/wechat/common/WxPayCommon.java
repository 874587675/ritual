package com.ruoyi.project.business.pay.wechat.common;


import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;

/**
 * @ClassName:WxPayCommon
 * @description:
 * @author: zgc
 **/
public class WxPayCommon {
    
    public static final String WECHATPAY_SERIAL = "Wechatpay-Serial";
    public static final String WECHATPAY_NONCE = "Wechatpay-Nonce";
    public static final String WECHATPAY_SIGNATURE = "Wechatpay-Signature";
    public static final String WECHATPAY_TIMESTAMP = "Wechatpay-Timestamp";
    public static final String WECHATPAY_SIGN_TYPE = "Wechatpay-Signature-Type";
    
    public static String getWxPayCacheKey(WeChatJsapiPayVO weChatJsapiPayVO) {
        return weChatJsapiPayVO.getPayer().getOpenid() + "/" + weChatJsapiPayVO.getOutTradeNo();
    }
}
