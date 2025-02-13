package com.ruoyi.project.business.service.impl;

import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.UserOptionMapper;
import com.ruoyi.project.business.domain.UserOption;
import com.ruoyi.project.business.service.UserOptionService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserOptionServiceImpl extends ServiceImpl<UserOptionMapper, UserOption> implements UserOptionService{
    @Resource
    private UserOptionMapper userOptionMapper;

    @Override
    @Transactional
    public String insertUserOption(UserOption userOption) {
        try{
            int insertResult = userOptionMapper.insert(userOption);
            if (insertResult <= 0) {
                log.error("添加用户意见反馈失败, 用户ID:{}", userOption.getUserId());
                throw new ServiceException("添加用户意见反馈失败");
            }
            log.info("添加用户意见反馈成功");
            return "添加用户意见反馈成功";
        }catch (Exception e){
            log.error("添加用户意见反馈失败, 用户ID:{}", userOption.getUserId());
            throw new ServiceException("添加用户意见反馈失败");
        }
    }
}
