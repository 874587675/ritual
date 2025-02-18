package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_coupon")
public class Coupon {
    /**
     * 主键，优惠券ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 优惠券码，用户领取或使用时需要的唯一标识
     */
    @TableField(value = "code")
    private String code;

    /**
     * 优惠券名称，例如：满100减20、8折优惠券等
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 优惠券描述，简单描述优惠券规则
     */
    @TableField(value = "description")
    private String description;

    /**
     * 优惠券数量
     */
    @TableField(value = "`count`")
    private Integer count;

    /**
     * 优惠券类型：1-满减，2-折扣，3-现金券等
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 优惠券的值（满减金额、折扣率、现金金额等）
     */
    @TableField(value = "`value`")
    private BigDecimal value;

    /**
     * 使用该优惠券的最小订单金额
     */
    @TableField(value = "min_order_amount")
    private BigDecimal minOrderAmount;

    /**
     * 优惠券有效开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 优惠券有效结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 状态：1-未使用，2-已使用，3-已过期
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 每个用户可领取的数量，默认为1
     */
    @TableField(value = "user_limit")
    private Integer userLimit;

    /**
     * 每张优惠券最大使用次数，默认为1
     */
    @TableField(value = "use_limit")
    private Integer useLimit;

    /**
     * 是否可与其他优惠券共享使用
     */
    @TableField(value = "can_be_used_with_other_coupons")
    private Integer canBeUsedWithOtherCoupons;

    /**
     * 优惠券类别，适用于不同的促销活动
     */
    @TableField(value = "coupon_category")
    private String couponCategory;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
