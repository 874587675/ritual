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
    // （1待支付 2已支付 3已取消 4已退款 5已关闭 6待发货 7待收货 8待评价 9已完成）
    NO_PAY(1, "待支付"),
    PAYED(2, "已支付"),
    CANCEL(3, "已取消"),
    REFUNDED(4, "已退款"),
    CLOSED(5, "已关闭"),
    WAIT_SHIP(6, "待发货"),
    WAIT_RECEIVE(7, "待收货"),
    WAIT_EVALUATE(8, "待评价"),
    COMPLETED(9, "已完成");
    
    private Integer code;
    private String status;
}
