package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.SigninRewards;
import lombok.Data;

@Data
public class SigninRewardsVO extends SigninRewards {
    /**
     * 签到状态
     */
    private Boolean signinStatus;

    /**
     * 连续签到天数
     */
    private Integer signinDays;
}
