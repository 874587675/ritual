package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.MissMail;
import com.ruoyi.project.business.domain.MissSound;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.MissMailMapper;
import com.ruoyi.project.business.mapper.MissSoundMapper;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.service.MissMailService;
import jdk.jpackage.internal.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class MissMailServiceImpl extends ServiceImpl<MissMailMapper, MissMail> implements MissMailService {

    @Resource
    private MissMailMapper missMailMapper;
    @Resource
    private MissSoundMapper missSoundMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public String insertMissMail(MissMail missMail) {
        try {
            MissSound missSound = missSoundMapper.selectById(missMail.getMissId());
            if (missSound == null){
                log.error("找不到此守护星信息");
                throw new ServiceException("找不到此守护星信息");
            }
            User user = userMapper.selectById(missMail.getUserId());
            if (user == null){
                log.error("找不到此用户信息");
                throw new ServiceException("找不到此用户信息");
            }
            missMail.setNickName(user.getNickname());
            missMail.setAvatar(user.getAvatar());
            missMailMapper.insert(missMail);
            Log.info("插入思念有音邮箱信息成功");
            return "插入思念有音邮箱信息成功";
        }catch (Exception e){
            log.error("插入思念有音邮箱信息失败", e);
            throw new ServiceException("插入思念有音邮箱信息失败");
        }
    }

    @Override
    @Transactional
    public String replyMissMail(MissMail missMail) {
        try {
            MissSound missSound = missSoundMapper.selectById(missMail.getMissId());
            if (missSound == null){
                log.error("找不到此守护星信息");
                throw new ServiceException("找不到此守护星信息");
            }
            missMailMapper.updateById(missMail);
            Log.info("回复思念有音邮箱信息成功");
            return "回复思念有音邮箱信息成功";
        }catch (Exception e){
            log.error("回复思念有音邮箱信息失败", e);
            throw new ServiceException("回复思念有音邮箱信息失败");
        }
    }

    @Override
    public IPage<MissMail> selectMissMailPage(Integer pageNo, Integer pageSize, Integer missId) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<MissMail>().eq(MissMail::getMissId,missId));
    }
}
