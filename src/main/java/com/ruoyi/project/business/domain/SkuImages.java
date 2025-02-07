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
 * sku图片
 */
@ApiModel(description="sku图片")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_sku_images")
public class SkuImages implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * sku_id
     */
    @TableField(value = "sku_id")
    @ApiModelProperty(value="sku_id")
    private Integer skuId;

    /**
     * 图片地址
     */
    @TableField(value = "img_url")
    @ApiModelProperty(value="图片地址")
    private String imgUrl;

    /**
     * 排序
     */
    @TableField(value = "img_sort")
    @ApiModelProperty(value="排序")
    private Integer imgSort;

    /**
     * 默认图[0 - 不是默认图，1 - 是默认图]
     */
    @TableField(value = "default_flag")
    @ApiModelProperty(value="默认图[0 - 不是默认图，1 - 是默认图]")
    private Integer defaultFlag;

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