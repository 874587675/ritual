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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_order")
@Builder
public class Order implements Serializable {
    /**
     * 订单表主键
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "订单表主键")
    private String id;

    /**
     * open_id编号
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value = "open_id")
    private String openId;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 订单总金额
     */
    @TableField(value = "amount")
    @ApiModelProperty(value = "订单总金额")
    private BigDecimal amount;

    /**
     * 订单标题
     */
    @TableField(value = "subject")
    @ApiModelProperty(value = "订单标题")
    private String subject;

    /**
     * 订单状态（1待支付 2未支付 3已支付 4已取消 5已退款）
     */
    @TableField(value = "order_status")
    @ApiModelProperty(value = "订单状态（1待支付 2未支付 3已支付 4已取消 5已退款）")
    private Integer orderStatus;

    /**
     * 订单状态的详细描述
     */
    @TableField(value = "order_status_desc")
    @ApiModelProperty(value = "订单状态的详细描述")
    private String orderStatusDesc;

    /**
     * 交易状态
     * 1支付成功
     * 2转入退款
     * 3未支付
     * 4已关闭
     * 5已撤销（仅付款码支付会返回）
     * 6用户支付中（仅付款码支付会返回）
     * 7支付失败（仅付款码支付会返回）
     */
    @TableField(value = "trade_status")
    @ApiModelProperty(value = "交易状态 ,1支付成功 ,2转入退款 ,3未支付 ,4已关闭  ,5已撤销（仅付款码支付会返回） ,6用户支付中（仅付款码支付会返回） ,7支付失败（仅付款码支付会返回）")
    private Integer tradeStatus;

    /**
     * 交易状态的详细描述
     */
    @TableField(value = "trade_status_desc")
    @ApiModelProperty(value = "交易状态的详细描述")
    private String tradeStatusDesc;
    
    /**
     * 订单创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "订单更新时间")
    private Date updateTime;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
