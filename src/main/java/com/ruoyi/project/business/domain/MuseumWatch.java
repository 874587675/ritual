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
@TableName(value = "t_museum_watch")
public class MuseumWatch {
    /**
     * 关注纪念馆记录表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户主键ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 纪念馆主键ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

    /**
     * 关注纪念馆状态
     */
    @TableField(value = "watch_status")
    private Integer watchStatus;

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
