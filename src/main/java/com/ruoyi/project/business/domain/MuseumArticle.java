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
@TableName(value = "t_museum_article")
public class MuseumArticle {
    /**
     * 馆藏文章表主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 馆藏文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 馆藏文章描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 馆藏文章图片
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 馆藏ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

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
