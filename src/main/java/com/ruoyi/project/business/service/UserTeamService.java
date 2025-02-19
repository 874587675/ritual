package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.UserTeam;
import com.ruoyi.project.business.vo.UserTeamVO;

public interface UserTeamService extends IService<UserTeam>{


    IPage<UserTeamVO> selectUserTeam(Integer pageNo, Integer pageSize, String userId);

    UserTeamVO selectForIncomeAndInviteUserCount(String userId);

    IPage<UserTeamVO> pageList(Integer pageNo, Integer pageSize);
}
