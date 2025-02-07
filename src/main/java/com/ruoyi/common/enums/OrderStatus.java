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
    // 1待支付 2未支付 3已支付 4已取消 5已退款
    WAIT_PAY(1, "待支付"),
    NO_PAY(2, "未支付"),
    PAYED(3, "已支付"),
    CANCEL(4, "已取消"),
    REFUNDED(5, "已退款");
    
    private Integer code;
    private String status;
}
