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
@TableName(value = "t_signin_rewards")
public class SigninRewards {
    /**
     * 签到奖励表主键ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 签到天数
     */
    @TableField(value = "signin_day")
    private Integer signinDay;

    /**
     * 奖励描述
     */
    @TableField(value = "reward_description")
    private String rewardDescription;

    /**
     * 关联的物品ID
     */
    @TableField(value = "item_id")
    private Integer itemId;

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
