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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_miss_sound")
public class MissSound implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "思念有音主键ID")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "逝者名字")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone")
    private String phone;

    @ApiModelProperty(value = "用户主键ID")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "纪念馆ID")
    @TableField(value = "museum_id")
    private Integer museumId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    private Date updateTime;


}
