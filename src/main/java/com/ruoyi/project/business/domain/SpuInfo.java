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
 * spu信息表
 */
@ApiModel(description="spu信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_spu_info")
public class SpuInfo implements Serializable {
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="商品id")
    private Integer id;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="商品名称")
    private String name;

    /**
     * 商品描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="商品描述（副标题）")
    private String description;

    /**
     * 所属分类id
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value="所属分类id")
    private Integer categoryId;
    
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    @TableField(value = "publish_status")
    @ApiModelProperty(value="上架状态[0 - 下架，1 - 上架]")
    private Integer publishStatus;

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
     * 商品介绍（富文本格式）
     */
    @TableField(value = "introduce")
    @ApiModelProperty(value="商品介绍（富文本格式）")
    private String introduce;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="逻辑删除状态（0-未删除 1-已删除）")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
