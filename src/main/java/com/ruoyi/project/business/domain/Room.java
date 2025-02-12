package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_room")
public class Room {
    /**
     * 房间表主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 房间名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 馆藏ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

    /**
     * 创建者用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 房间背景ID
     */
    @TableField(value = "room_back_id")
    private Integer roomBackId;
    
    /**
     * 房间状态
     * 1 开放
     * 2 关闭
     * 3 封禁 4私密
     */
    @TableField(value = "room_status")
    private Integer roomStatus;

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
