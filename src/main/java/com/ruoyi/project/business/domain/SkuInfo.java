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

/**
 * sku信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_sku_info")
public class SkuInfo {
    private static final long serialVersionUID = 1L;
    /**
     * sku ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * spu ID
     */
    @TableField(value = "spu_id")
    private Integer spuId;

    /**
     * 所属分类id
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    private Integer brandId;

    /**
     * 默认图片
     */
    @TableField(value = "sku_default_img")
    private String skuDefaultImg;

    /**
     * 标题
     */
    @TableField(value = "sku_title")
    private String skuTitle;

    /**
     * 副标题
     */
    @TableField(value = "sku_subtitle")
    private String skuSubtitle;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 原价
     */
    @TableField(value = "origin_price")
    private BigDecimal originPrice;

    /**
     * 销量
     */
    @TableField(value = "sale_count")
    private Long saleCount;

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

    /**
     * 库存
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 是否有效，0无效，1有效
     */
    @TableField(value = "`enable`")
    private Integer enable;
}
