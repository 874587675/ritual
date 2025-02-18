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
@TableName(value = "t_address")
public class Address {
    /**
     * 收货地址表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 收货人用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 收货人用户名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 收货人手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 收货所在地区
     */
    @TableField(value = "area")
    private String area;
    
    /**
     * 收货人详细地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 是否是默认地址（0 否 1是）
     */
    @TableField(value = "is_default")
    private Integer isDefault;

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
