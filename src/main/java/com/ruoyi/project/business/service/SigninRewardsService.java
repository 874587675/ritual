package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.SigninRewards;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.SigninRewardsVO;

public interface SigninRewardsService extends IService<SigninRewards>{

    SigninRewardsVO checkTodaySignin(String userId);

    String signin(String userId);
}
