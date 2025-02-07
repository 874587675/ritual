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
 * 商品属性
 */
@ApiModel(description="商品属性")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_attribute")
public class Attribute implements Serializable {
    /**
     * 属性id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="属性id")
    private Integer id;

    /**
     * 属性名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="属性名")
    private String name;

    /**
     * 属性图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value="属性图标")
    private String icon;
    

    /**
     * 属性类型 0-销售属性(sku)，1-基本属性(spu)
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="属性类型 0-销售属性(sku)，1-基本属性(spu)")
    private Integer type;

    /**
     * 启用状态 0 - 禁用，1 - 启用
     */
    @TableField(value = "`enable`")
    @ApiModelProperty(value="启用状态 0 - 禁用，1 - 启用")
    private Integer enable;

    /**
     * 所属分类id
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value="所属分类id")
    private Integer categoryId;

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
