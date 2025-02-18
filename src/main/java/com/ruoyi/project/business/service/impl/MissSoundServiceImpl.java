package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.MissSound;
import com.ruoyi.project.business.mapper.MissSoundMapper;
import com.ruoyi.project.business.service.MissSoundService;
import com.ruoyi.project.business.util.aliyun.sms.SmsUtil;
import com.ruoyi.project.business.vo.MissSoundVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Slf4j
public class MissSoundServiceImpl extends ServiceImpl<MissSoundMapper, MissSound> implements MissSoundService {

    @Resource
    private MissSoundMapper missSoundMapper;
    @Resource
    private SmsUtil smsUtil;

    @Override
    @Transactional
    public String createStar(MissSoundVO missSoundVO) {
        try {
            // 验证手机验证码是否正确
            boolean verified = smsUtil.verifyPhoneCode(missSoundVO.getPhone(), missSoundVO.getCode());
            if (!verified) {
                log.error("验证码不正确");
                throw new ServiceException("验证码不正确,请确认后重新输入");
            }
            int insertResult = missSoundMapper.insert(missSoundVO);
            if (insertResult == 0) {
                log.error("创建守护星失败");
                throw new ServiceException("创建守护星失败,请重新填写信息");
            }
            log.info("创建守护星成功");
            return "创建守护星成功";
        }catch (Exception e) {
            log.error("创建守护星出错: {}", e.getMessage());
            throw new ServiceException("创建守护星出错, 请重新填写信息");
        }
    }

    @Override
    public IPage<MissSound> myStars(Integer pageNo, Integer pageSize, Integer userId) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<MissSound>().eq(MissSound::getUserId, userId));
    }
}
