package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName:TradeType
 * @description: 交易状态
 * @author: zgc
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TradeType {
    /*
    JSAPI：公众号支付、小程序支付
    NATIVE：Native支付
    APP：APP支付
    MICROPAY：付款码支付
    MWEB：H5支付
    FACEPAY：刷脸支付
    */
    
    // 1公众号或小程序支付 2Native支付 3APP支付 4付款码支付 5H5支付 6刷脸支付
    JSAPI(1,"公众号或小程序支付"),
    NATIVE(2,"Native支付"),
    APP(3,"APP支付"),
    MICROPAY(4,"付款码支付"),
    MWEB(5,"H5支付"),
    FACEPAY(6,"刷脸支付");
    
    private Integer code;
    private String status;
    
    
}
