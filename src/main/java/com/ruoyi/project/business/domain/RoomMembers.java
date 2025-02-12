package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "t_room_members")
public class RoomMembers {
    /**
     * 房间成员表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 房间表ID
     */
    @TableField(value = "room_id")
    private Integer roomId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 成员角色
        1房主
        2管理员
        3普通成员
     */
    @TableField(value = "`role`")
    private Integer role;

    /**
     * 礼物积分
     */
    @TableField(value = "gift_score")
    private Integer giftScore;
    
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
     * 逻辑删除（0 未删除  1已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
