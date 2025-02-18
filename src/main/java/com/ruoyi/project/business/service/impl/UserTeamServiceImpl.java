package com.ruoyi.project.business.service.impl;

import com.alipay.api.domain.UserInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.vo.UserTeamVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.UserTeamMapper;
import com.ruoyi.project.business.domain.UserTeam;
import com.ruoyi.project.business.service.UserTeamService;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam> implements UserTeamService{
    @Resource
    private UserTeamMapper userTeamMapper;

    @Override
    public IPage<UserTeamVO> selectUserTeam(Integer pageNo, Integer pageSize, String userId) {
        //分页查询出团队里的成员以及成员信息
        return userTeamMapper.selectUserTeam(new Page<>(pageNo, pageSize), userId);
    }

    @Override
    public UserTeamVO selectForIncomeAndInviteUserCount(String userId) {
        //查出该用户的团队收益和邀请的人数
        return userTeamMapper.selectForIncomeAndInviteUserCount(userId);  
    }
}
