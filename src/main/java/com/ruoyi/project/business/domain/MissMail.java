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
@TableName("t_miss_mail")
public class MissMail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "信箱主键ID")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户主键ID")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "用户昵称")
    @TableField(value = "nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    @TableField(value = "avatar")
    private String avatar;

    @ApiModelProperty(value = "提问")
    @TableField(value = "question")
    private String question;

    @ApiModelProperty(value = "回答")
    @TableField(value = "answer")
    private String answer;

    @ApiModelProperty(value = "思念有音主键ID")
    @TableField(value = "miss_id")
    private Integer missId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    private Date updateTime;


}
