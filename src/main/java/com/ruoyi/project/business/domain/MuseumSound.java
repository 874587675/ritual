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
@TableName(value = "t_museum_sound")
public class MuseumSound {
    /**
     * 馆藏音乐主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 馆藏音乐名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 背景图片地址
     */
    @TableField(value = "sound_url")
    private String soundUrl;

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
