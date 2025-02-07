package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sku销售属性值
 */
@ApiModel(description = "sku销售属性值")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_sku_sale_attribute_value")
public class SkuSaleAttributeValue implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * spu_id
     */
    @TableField(value = "spu_id")
    @ApiModelProperty(value = "spu_id")
    private Integer spuId;

    /**
     * sku_id
     */
    @TableField(value = "sku_id")
    @ApiModelProperty(value = "sku_id")
    private Integer skuId;

    /**
     * attribute_id
     */
    @TableField(value = "attribute_id")
    @ApiModelProperty(value = "attribute_id")
    private Integer attributeId;

    /**
     * 销售属性名
     */
    @TableField(value = "attribute_name")
    @ApiModelProperty(value = "销售属性名")
    private String attributeName;

    /**
     * 销售属性值
     */
    @TableField(value = "attribute_value")
    @ApiModelProperty(value = "销售属性值")
    private String attributeValue;

    /**
     * 顺序
     */
    @TableField(value = "attribute_sort")
    @ApiModelProperty(value = "顺序")
    private Integer attributeSort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}