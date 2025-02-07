package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "t_order_success")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order_success")
public class OrderSuccess implements Serializable {
    /**
     * 订单支付成功表主键
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "订单支付成功表主键")
    private String id;

    /**
     * 订单表主键
     */
    @TableField(value = "order_id")
    @ApiModelProperty(value = "订单表主键")
    private String orderId;

    /**
     * 货币类型
     */
    @TableField(value = "currency")
    @ApiModelProperty(value = "货币类型")
    private String currency;

    /**
     * 用户支付币种类型
     */
    @TableField(value = "payer_currency")
    @ApiModelProperty(value = "用户支付币种类型")
    private String payerCurrency;

    /**
     * 订单用户支付金额
     */
    @TableField(value = "payer_total")
    @ApiModelProperty(value = "订单用户支付金额")
    private BigDecimal payerTotal;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    /**
     * 银行类型
     * 银行简码_具体类型
     * (DEBIT借记卡/CREDIT信用卡/ECNY数字人民币)，
     * 例如ICBC_DEBIT代表工商银行借记卡，
     * 非银行卡支付类型(例如余额/零钱通等)统一为OTHERS
     * 具体请参考《银行类型对照表》。
     */
    @TableField(value = "bank_type")
    @ApiModelProperty(value = "银行类型,银行简码_具体类型  ,(DEBIT借记卡/CREDIT信用卡/ECNY数字人民币)， ,例如ICBC_DEBIT代表工商银行借记卡， ,非银行卡支付类型(例如余额/零钱通等)统一为OTHERS,具体请参考《银行类型对照表》。")
    private String bankType;

    /**
     * 交易类型（1公众号或小程序支付 2Native支付 3APP支付 4付款码支付 5H5支付 6刷脸支付）
     */
    @TableField(value = "trade_type")
    @ApiModelProperty(value = "交易类型（1公众号或小程序支付 2Native支付 3APP支付 4付款码支付 5H5支付 6刷脸支付）")
    private Integer tradeType;

    /**
     * 支付完成创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "支付完成创建时间")
    private Date createTime;

    /**
     * 支付完成更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "支付完成更新时间")
    private Date updateTime;

    /**
     * 微信支付订单号
     */
    @TableField(value = "transaction_id")
    @ApiModelProperty(value = "微信支付订单号")
    private String transactionId;

    private static final long serialVersionUID = 1L;
}
