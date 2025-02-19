package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2025-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "轮播图表主键ID")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "轮播图名字")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "轮播图描述")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty(value = "图片地址")
    @TableField(value = "picture")
    private String picture;

    @ApiModelProperty(value = "排序")
    @TableField(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    private Date updateTime;
}
