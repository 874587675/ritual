package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sku信息
 */
@ApiModel(description="sku信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_sku_info")
public class SkuInfo implements Serializable {
    /**
     * sku ID
     */
    @TableId(value = "sku_id", type = IdType.NONE)
    @ApiModelProperty(value="sku ID")
    private Integer skuId;

    /**
     * spu ID
     */
    @TableField(value = "spu_id")
    @ApiModelProperty(value="spu ID")
    private Integer spuId;

    /**
     * sku名称
     */
    @TableField(value = "sku_name")
    @ApiModelProperty(value="sku名称")
    private String skuName;

    /**
     * 所属分类id
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value="所属分类id")
    private Integer categoryId;

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    @ApiModelProperty(value="品牌id")
    private Integer brandId;

    /**
     * 默认图片
     */
    @TableField(value = "sku_default_img")
    @ApiModelProperty(value="默认图片")
    private String skuDefaultImg;

    /**
     * 标题
     */
    @TableField(value = "sku_title")
    @ApiModelProperty(value="标题")
    private String skuTitle;

    /**
     * 副标题
     */
    @TableField(value = "sku_subtitle")
    @ApiModelProperty(value="副标题")
    private String skuSubtitle;

    /**
     * 价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value="价格")
    private BigDecimal price;

    /**
     * 销量
     */
    @TableField(value = "sale_count")
    @ApiModelProperty(value="销量")
    private Long saleCount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}