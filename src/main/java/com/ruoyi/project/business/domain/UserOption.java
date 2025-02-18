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
@TableName(value = "t_user_option")
public class UserOption {
    /**
     * 意见表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 反馈意见的类型
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 反馈的意见内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 反馈的图片
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 意见的状态（0 未处理 1正在处理 2已处理）
     */
    @TableField(value = "option_status")
    private Integer optionStatus;

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
