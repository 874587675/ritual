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
@TableName(value = "t_heart_tree")
public class HeartTree {
    /**
     * 心灵树洞表主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户主键ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;
    
    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 纪念馆主键ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

    /**
     * 纪念馆名称
     */
    @TableField(value = "museum_name")
    private String museumName;

    /**
     * 图片
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 视频
     */
    @TableField(value = "video")
    private String video;

    /**
     * 点赞数量
     */
    @TableField(value = "like_count")
    private Integer likeCount;

    /**
     * 留言数量
     */
    @TableField(value = "message_count")
    private Integer messageCount;

    /**
     * 匿名状态（0 不匿名 1匿名）
     */
    @TableField(value = "anonymous_status")
    private Integer anonymousStatus;

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
     * 逻辑删除（0未删除，1已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
