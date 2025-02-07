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
 * 属性组和属性规格关联表表id
 */
@ApiModel(description="属性组和属性规格关联表表id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_attribute_group_relation")
public class AttributeGroupRelation implements Serializable {
    /**
     * 属性组和属性关联表id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="属性组和属性关联表id")
    private Integer id;

    /**
     * 属性id
     */
    @TableField(value = "attribute_id")
    @ApiModelProperty(value="属性id")
    private Integer attributeId;

    /**
     * 属性分组id
     */
    @TableField(value = "attribute_group_id")
    @ApiModelProperty(value="属性分组id")
    private Integer attributeGroupId;

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
