package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName:TradeStatus
 * @description: 交易状态枚举类
 * @author: zgc
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TradeStatus {
    /*
    SUCCESS：支付成功
    REFUND：转入退款
    NOT_PAY：未支付
    CLOSED：已关闭
    REVOKED：已撤销（仅付款码支付会返回）
    USER_PAYING：用户支付中（仅付款码支付会返回）
    PAY_ERROR：支付失败（仅付款码支付会返回）
    */

    // 1支付成功 2转入退款 3未支付 4已关闭 5已撤销（仅付款码支付会返回） 6用户支付中（仅付款码支付会返回） 7支付失败（仅付款码支付会返回）
    SUCCESS(1,"支付成功"),
    REFUND(2,"转入退款"),
    NOT_PAY(3,"未支付"),
    CLOSED(4,"已关闭"),
    REVOKED(5,"已撤销"),
    USER_PAYING(6,"用户支付中"),
    PAY_ERROR(7,"支付失败");
    
    private Integer code;
    private String status;
}
