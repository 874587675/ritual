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
 * 商品三级分类
 */
@ApiModel(description="商品三级分类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_category")
public class Category implements Serializable {
    /**
     * 分类id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="分类id")
    private Integer id;

    /**
     * 分类名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="分类名称")
    private String name;

    /**
     * 父分类id
     */
    @TableField(value = "parent_category_id")
    @ApiModelProperty(value="父分类id")
    private Integer parentCategoryId;

    /**
     * 分类层级（0代表顶级分类）
     */
    @TableField(value = "category_level")
    @ApiModelProperty(value="分类层级（0代表顶级分类）")
    private Integer categoryLevel;

    /**
     * 0不显示，1显示
     */
    @TableField(value = "show_flag")
    @ApiModelProperty(value="0不显示，1显示")
    private Integer showFlag;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序")
    private Integer sort;

    /**
     * 图标地址
     */
    @TableField(value = "icon")
    @ApiModelProperty(value="图标地址")
    private String icon;

    /**
     * 计量单位
     */
    @TableField(value = "product_unit")
    @ApiModelProperty(value="计量单位")
    private String productUnit;

    /**
     * 分类创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="分类创建时间")
    private Date createTime;

    /**
     * 分类更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="分类更新时间")
    private Date updateTime;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}