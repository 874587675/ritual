package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_brand")
public class Brand {
    /**
     * 品牌id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 品牌名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 品牌图片地址
     */
    @TableField(value = "image")
    private String image;

    /**
     * 品牌的首字母
     */
    @TableField(value = "letter")
    private String letter;

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
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
