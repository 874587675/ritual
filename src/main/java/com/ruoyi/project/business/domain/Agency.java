package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_agency")
public class Agency {
    /**
     * 城市代理商信息表
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 代理商名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 代理商联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 代理区域名称
     */
    @TableField(value = "area")
    private String area;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户身份证正面
     */
    @TableField(value = "id_card_z")
    private String idCardZ;

    /**
     * 用户身份证反面
     */
    @TableField(value = "id_card_f")
    private String idCardF;

    /**
     * 审核状态
0 待审核
1 审核通过
2 审核未通过
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
