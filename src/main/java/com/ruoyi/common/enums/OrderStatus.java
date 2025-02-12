package com.ruoyi.common.enums;

import lombok.*;

/**
 * @ClassName:OrderStatus
 * @description: 订单状态枚举类
 * @author: zgc
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum OrderStatus {
    // （1未支付 2已支付 3已取消 4已退款）
    NO_PAY(1, "未支付"),
    PAYED(2, "已支付"),
    CANCEL(3, "已取消"),
    REFUNDED(4, "已退款"),
    CLOSED(5, "已关闭");
    private Integer code;
    private String status;
}
