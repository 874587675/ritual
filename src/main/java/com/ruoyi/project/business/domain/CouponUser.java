package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_coupon_user")
public class CouponUser {
    /**
     * 主键，记录ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户ID，表示哪个用户领取了优惠券
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 优惠券表的ID
     */
    @TableField(value = "coupon_id")
    private Integer couponId;

    /**
     * 领取的优惠券码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 领取状态：1-未使用，2-已使用，3-过期
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 领取时间
     */
    @TableField(value = "received_time")
    private Date receivedTime;

    /**
     * 使用时间
     */
    @TableField(value = "used_time")
    private Date usedTime;

    /**
     * 过期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

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

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
