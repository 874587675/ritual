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
@TableName(value = "t_message")
public class Message {
    /**
     * 消息表主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 顶级消息ID
     */
    @TableField(value = "top_id")
    private Integer topId;

    /**
     * 父消息ID
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 房间主键ID
     */
    @TableField(value = "room_id")
    private Integer roomId;

    /**
     * 纪念馆主键ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

    /**
     * 心灵树洞主键ID
     */
    @TableField(value = "tree_id")
    private Integer treeId;

    /**
     * 消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 发送者ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 发送者昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 回复者ID
     */
    @TableField(value = "reply_user_id")
    private String replyUserId;

    /**
     * 回复者昵称
     */
    @TableField(value = "reply_name")
    private String replyName;

    /**
     * 点赞数
     */
    @TableField(value = "like_count")
    private Integer likeCount;

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
     * 逻辑删除（0 未删除，1已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
