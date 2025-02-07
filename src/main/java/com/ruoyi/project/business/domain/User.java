package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
@Builder
public class User implements Serializable {
    /**
     * 用户编号
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "用户编号")
    private String id;

    /**
     * 微信编号openId
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value = "微信编号openId")
    private String openId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户昵称
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 用户状态(1-正常 2-冻结)
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "用户状态(1-正常 2-冻结)")
    private Integer status;

    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 邮箱地址
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱地址")
    private String email;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 历史手机号
     */
    @TableField(value = "history_phone")
    @ApiModelProperty(value = "历史手机号")
    private String historyPhone;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableLogic
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
