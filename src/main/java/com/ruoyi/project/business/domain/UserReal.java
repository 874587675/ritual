package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "t_user_real")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user_real")
public class UserReal implements Serializable {
    /**
     * 用户真实信息表主键ID
     */
    @TableId(value = "id", type = IdType.NONE)
    @ApiModelProperty(value = "用户真实信息表主键ID")
    private String id;

    /**
     * 用户表主键ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户表主键ID")
    private String userId;

    /**
     * 用户真实姓名
     */
    @TableField(value = "real_name")
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    /**
     * 用户身份证号
     */
    @TableField(value = "id_card_number")
    @ApiModelProperty(value = "用户身份证号")
    private String idCardNumber;

    /**
     * 用户身份证正面照片
     */
    @TableField(value = "id_card_front")
    @ApiModelProperty(value = "用户身份证正面照片")
    private String idCardFront;

    /**
     * 用户身份证反面照片
     */
    @TableField(value = "id_card_back")
    @ApiModelProperty(value = "用户身份证反面照片")
    private String idCardBack;

    /**
     * 用户手持身份证照片
     */
    @TableField(value = "id_card_hand")
    @ApiModelProperty(value = "用户手持身份证照片")
    private String idCardHand;

    /**
     * 用户身份证地址
     */
    @TableField(value = "id_card_address")
    @ApiModelProperty(value = "用户身份证地址")
    private String idCardAddress;

    /**
     * 用户籍贯
     */
    @TableField(value = "domicile")
    @ApiModelProperty(value = "用户籍贯")
    private String domicile;

    /**
     * 真实年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value = "真实年龄")
    private Integer age;

    /**
     * 真实性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "真实性别")
    private String sex;

    /**
     * 生肖属相
     */
    @TableField(value = "zodiac")
    @ApiModelProperty(value = "生肖属相")
    private String zodiac;

    /**
     * 出生日期
     */
    @TableField(value = "birthday")
    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    /**
     * 实名状态（0-未实名  1-已实名 ）
     */
    @TableField(value = "is_real")
    @ApiModelProperty(value = "实名状态（0-未实名  1-已实名 ）")
    private Integer isReal;

    /**
     * 逻辑删除状态（0-未删除 1-已删除）
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除状态（0-未删除 1-已删除）")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
