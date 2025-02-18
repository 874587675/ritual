package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.framework.redis.BitComponent;
import com.ruoyi.project.business.domain.SigninRewards;
import com.ruoyi.project.business.mapper.SigninRewardsMapper;
import com.ruoyi.project.business.service.SigninRewardsService;
import com.ruoyi.project.business.vo.SigninRewardsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class SigninRewardsServiceImpl extends ServiceImpl<SigninRewardsMapper, SigninRewards> implements SigninRewardsService{

    @Resource
    private SigninRewardsMapper signinRewardsMapper;
    @Resource
    private BitComponent bitComponent;

    @Override
    public SigninRewardsVO checkTodaySignin(String userId) {
        SigninRewardsVO signinRewardsVO = new SigninRewardsVO();
        signinRewardsVO.setSigninStatus(bitComponent.hasSignedInToday(userId));
        signinRewardsVO.setSigninDays(bitComponent.getContinuousSignInDays(userId));
        return signinRewardsVO;
    }

    @Override
    @Transactional
    public String signin(String userId) {
        boolean hasKey = bitComponent.signIn(userId);
        if (hasKey) {
            log.info("用户{}，今日{}，已签到", userId,new Date());
            return "签到成功";
        }
        log.error("用户{}，今日{}，签到失败", userId, new Date());
        return "签到失败";
    }
}
