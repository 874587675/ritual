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
@TableName(value = "t_spirit_position")
public class SpiritPosition {
    /**
     * 祠堂牌位ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 祠堂馆ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

    /**
     * 牌位名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 牌位描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 个人纪念馆ID
     */
    @TableField(value = "self_museum_id")
    private Integer selfMuseumId;

    /**
     * 行数
     */
    @TableField(value = "`row`")
    private Integer row;

    /**
     * 纵数
     */
    @TableField(value = "col")
    private Integer col;

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
