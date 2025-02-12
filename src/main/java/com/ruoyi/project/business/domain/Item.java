package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_item")
public class Item {
    /**
     * 虚拟物品主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 虚拟物品名
     */
    @TableField(value = "`name`")
    private String name;

   
    
    /**
     * 虚拟物品描述
     */
    @TableField(value = "description")
    private String description;
    
    /**
     * 物品种类ID
     */
    @TableField(value = "type_id")
    private Integer typeId;

    /**
     * 物品图片
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 物品积分
     */
    @TableField(value = "score")
    private Double score;

    /**
     * 有效期（天数）
     */
    @TableField(value = "validate_days")
    private Integer validateDays;
    
    /**
     * 物品价格
     */
    @TableField(value = "price")
    private BigDecimal price;

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
