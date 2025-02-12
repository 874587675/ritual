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
 * 商品分类和品牌的中间表，两者是多对多关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_category_brand")
public class CategoryBrand {
    /**
     * 商品品牌关联表主键id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 商品类目id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    private Integer brandId;

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
