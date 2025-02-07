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

@ApiModel(description="t_spu_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_spu_image")
public class SpuImage implements Serializable {
    /**
     * spu 图片表id
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value="spu 图片表id")
    private Integer id;

    /**
     * spu主键id
     */
    @TableField(value = "spu_id")
    @ApiModelProperty(value="spu主键id")
    private Integer spuId;

    /**
     * 图片名
     */
    @TableField(value = "img_name")
    @ApiModelProperty(value="图片名")
    private String imgName;

    /**
     * 图片地址
     */
    @TableField(value = "img_url")
    @ApiModelProperty(value="图片地址")
    private String imgUrl;

    /**
     * 顺序
     */
    @TableField(value = "img_sort")
    @ApiModelProperty(value="顺序")
    private Integer imgSort;

    /**
     * 是否默认图（0否 1是）
     */
    @TableField(value = "default_flag")
    @ApiModelProperty(value="是否默认图（0否 1是）")
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