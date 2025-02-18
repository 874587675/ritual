package com.ruoyi.project.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user_team")
public class UserTeam {
    /**
     * 用户团队主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 顶级团长用户ID
     */
    @TableField(value = "top_user_id")
    private String topUserId;

    /**
     * 直接邀请用户ID
     */
    @TableField(value = "direct_user_id")
    private String directUserId;

    /**
     * 分成收益
     */
    @TableField(value = "income")
    private BigDecimal income;

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
