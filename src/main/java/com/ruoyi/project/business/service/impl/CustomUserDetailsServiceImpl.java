package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysPasswordService;
import com.ruoyi.framework.security.service.UserDetailsServiceImpl;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.system.domain.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysPasswordService passwordService;

//    @Autowired
//    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrPhone) {
        User userByUsername = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername, usernameOrPhone)
        );
        if (userByUsername == null) {
            throw new ServiceException("用户不存在");
        }
        passwordService.validate(userByUsername); //验证
        return new LoginUser(userByUsername);
    }
}
