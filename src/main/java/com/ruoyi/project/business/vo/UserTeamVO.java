package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.domain.UserTeam;
import lombok.Data;

/**
 * @ClassName:UserTeamVO
 * @description:
 * @author: zgc
 **/
@Data
public class UserTeamVO extends UserTeam {
    /**
     * 用户的总收益情况
     */
    private Double totalIncome;
    
    /**
     * 用户成功邀请的总人数
     */
    private Integer inviteUserCount;

    /**
     * 直接用户的对象
     */
    private User user;
}
