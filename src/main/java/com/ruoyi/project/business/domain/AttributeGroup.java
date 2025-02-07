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
 * 属性分组表
 */
@ApiModel(description="属性分组表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_attribute_group")
public class AttributeGroup implements Serializable {
    /**
     * 分组id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="分组id")
    private Integer id;

    /**
     * 组名
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="组名")
    private String name;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value="排序")
    private Integer sort;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 组图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value="组图标")
    private String icon;

    /**
     * 所属分类id
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value="所属分类id")
    private Integer categoryId;

    /**
     * 分组创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="分组创建时间")
    private Date createTime;

    /**
     * 分组更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="分组更新时间")
    private Date updateTime;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}