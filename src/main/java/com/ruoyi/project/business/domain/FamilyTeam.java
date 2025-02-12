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
@TableName(value = "t_family_team")
public class FamilyTeam {
    /**
     * 亲友团主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 亲友团名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 亲友团描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 亲友团二维码图片
     */
    @TableField(value = "qr_code")
    private String qrCode;

    /**
     * 馆藏主键ID
     */
    @TableField(value = "museum_id")
    private Integer museumId;

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
