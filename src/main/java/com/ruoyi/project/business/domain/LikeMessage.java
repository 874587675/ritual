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
@TableName(value = "t_like_message")
public class LikeMessage {
    /**
     * 消息点赞表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 消息主键ID
     */
    @TableField(value = "message_id")
    private Integer messageId;

    /**
     * 用户主键ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 消息点赞状态
     */
    @TableField(value = "like_status")
    private Integer likeStatus;

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
