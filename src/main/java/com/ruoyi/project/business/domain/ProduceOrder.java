package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_produce_order")
public class ProduceOrder {
    /**
     * 订单表主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * user_id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * open_id
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 订单总金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 订单标题
     */
    @TableField(value = "subject")
    private String subject;

    /**
     * 订单状态
     * 1待支付 
     * 2已支付 
     * 3已取消 
     * 4已退款 
     * 5已关闭
     * 6待发货
     * 7待收货
     * 8待评价
     * 9已完成
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

    /**
     * 订单状态的详细描述
     */
    @TableField(value = "order_status_desc")
    private String orderStatusDesc;

    /**
     * 交易状态 
    1支付成功 
    2转入退款 
    3未支付 
    4已关闭  
    5已撤销（仅付款码支付会返回） 
    6用户支付中（仅付款码支付会返回） 
    7支付失败（仅付款码支付会返回）
     */
    @TableField(value = "trade_status")
    private Integer tradeStatus;

    /**
     * 交易状态的详细描述
     */
    @TableField(value = "trade_status_desc")
    private String tradeStatusDesc;

    /**
     * 订单创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 收货地址ID
     */
    @TableField(value = "address_id")
    private Integer addressId;

    /**
     * 收货地址名称
     */
    @TableField(value = "address_name")
    private String addressName;

    /**
     * 商品图片
     */
    @TableField(value = "produce_image")
    private String produceImage;

    /**
     * 商品名称
     */
    @TableField(value = "produce_name")
    private String produceName;

    /**
     * 商品单价
     */
    @TableField(value = "produce_price")
    private BigDecimal producePrice;

    /**
     * 商品数量
     */
    @TableField(value = "produce_count")
    private Integer produceCount;

    /**
     * 商品规格名称
     */
    @TableField(value = "attribute_name")
    private String attributeName;

    /**
     * spuID主键
     */
    @TableField(value = "spu_id")
    private Integer spuId;

    /**
     * skuID主键
     */
    @TableField(value = "sku_id")
    private Integer skuId;

    /**
     * 运费
     */
    @TableField(value = "shipping_fee")
    private BigDecimal shippingFee;

    /**
     * 优惠券ID
     */
    @TableField(value = "coupon_id")
    private Integer couponId;

    /**
     * 优惠券名称
     */
    @TableField(value = "coupon_name")
    private String couponName;

    /**
     * 优惠券描述
     */
    @TableField(value = "coupon_descrption")
    private String couponDescrption;

    /**
     * 最终应付金额
     */
    @TableField(value = "final_price")
    private BigDecimal finalPrice;

    /**
     * 备注
     */
    @TableField(value = "note")
    private String note;

    /**
     * 支付方式（1-微信支付，2钱包零钱支付）
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 支付方式名称
     */
    @TableField(value = "pay_type_name")
    private String payTypeName;
    
    /**
     * 物流信息
     */
    @TableField(value = "logistics_info")
    private String logisticsInfo;
}
