package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.project.business.util.aliyun.sms.SmsUtil;
import com.ruoyi.project.business.vo.MuseumInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.MuseumInfoMapper;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.service.MuseumInfoService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MuseumInfoServiceImpl extends ServiceImpl<MuseumInfoMapper, MuseumInfo> implements MuseumInfoService {

    @Resource
    private MuseumInfoMapper museumInfoMapper;

    @Resource
    private SmsUtil smsUtil;

    @Override
    public IPage<MuseumInfo> selectMuseumInfoPage(Integer pageNo, Integer pageSize, Integer type) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<MuseumInfo>().eq(MuseumInfo::getType, type));
    }

    @Override
    public IPage<MuseumInfo> selectTodayMuseumInfo(Integer pageNo, Integer pageSize) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<MuseumInfo>()
                .eq(MuseumInfo::getStatus, 1)
                .or()
                .eq(MuseumInfo::getStatus, 2)
                .orderByDesc(MuseumInfo::getCreateTime));
    }

    @Override
    public IPage<MuseumInfo> selectNewMuseumInfo(Integer pageNo, Integer pageSize) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<MuseumInfo>()
                .orderByDesc(MuseumInfo::getCreateTime));
    }

    @Override
    @Transactional
    public String createMuseumInfoSelfByUserId(MuseumInfoVO museumInfoVO) {
        try {
            //验证手机验证码是否正确
            boolean result = smsUtil.verifyPhoneCode(museumInfoVO.getPhone(), museumInfoVO.getCode());
            if (!result) {
                throw new ServiceException("输入验证码有误，请确认后重新输入");
            }
            try {
                //判断用户选择的出生日期和逝世日期是否是农历，若是农历则转为公历存储一份
                if (museumInfoVO.getIsLunarBirth() == 1) {
                    museumInfoVO.setBirthDate(DateUtils.lunarToSolar(museumInfoVO.getBirthDateLunar()));
                }
                if (museumInfoVO.getIsLunarDeath() == 1) {
                    museumInfoVO.setDeathDate(DateUtils.lunarToSolar(museumInfoVO.getDeathDateLunar()));
                }
            } catch (Exception e) {
                log.error("日期转换失败", e);
                throw new ServiceException("日期转换失败");
            }

            // 初始化默认曝光和人气值
            museumInfoVO.setExposure(0);
            museumInfoVO.setPopularity(0);
            museumInfoVO.setIsPublic(1);

            save(museumInfoVO);
            return "创建个人馆成功";
        } catch (Exception e) {
            log.error("创建个人馆失败: ", e);
            throw new ServiceException("创建个人馆失败");
        }
    }

    @Override
    @Transactional
    public String createMuseumInfoFamilyByUserId(MuseumInfoVO museumInfoVO) {
        try {
            // 初始化默认曝光和人气值
            museumInfoVO.setExposure(0);
            museumInfoVO.setPopularity(0);

            museumInfoMapper.insert(museumInfoVO);
            return "创建祠堂馆成功";
        } catch (Exception e) {
            log.error("创建祠堂馆失败: ", e);
            throw new ServiceException("创建祠堂馆失败");
        }
    }

    @Override
    public IPage<MuseumInfo> selectMuseumInfoSelfByUserId(Integer pageNo, Integer pageSize, Integer userId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<MuseumInfo>().eq(MuseumInfo::getUserId, userId).orderByDesc(MuseumInfo::getUpdateTime));
    }

    @Override
    @Transactional
    public String updatePictureByMuseumId(Integer museumId, String picture) {
        try {
            MuseumInfo museumInfo = getById(museumId);
            if (museumInfo == null) {
                throw new ServiceException("该纪念馆不存在");
            }
            boolean update = update(new LambdaUpdateWrapper<MuseumInfo>().set(MuseumInfo::getPicture, picture).eq(MuseumInfo::getId, museumId));
            if (update) {
                log.info("纪念馆ID:{},更新相册图片成功", museumId);
                return "更新相册图片成功";
            } else {
                log.error("纪念馆ID:{},更新相册图片失败", museumId);
                throw new ServiceException("更新相册图片失败");
            }
        } catch (Exception e) {
            log.error("更新相册图片失败", e);
            throw new ServiceException("更新相册图片失败");
        }
    }

    @Override
    @Transactional
    public String updateVideoByMuseumId(Integer museumId, String video) {
        try {
            MuseumInfo museumInfo = getById(museumId);
            if (museumInfo == null) {
                log.error("该纪念馆不存在");
                throw new ServiceException("该纪念馆不存在");
            }
            boolean update = update(new LambdaUpdateWrapper<MuseumInfo>().set(MuseumInfo::getVideo, video).eq(MuseumInfo::getId, museumId));
            if (update) {
                log.info("纪念馆ID:{},更新视频成功", museumId);
                return "更新视频成功";
            } else {
                log.error("纪念馆ID:{},更新视频失败", museumId);
                throw new ServiceException("更新视频失败");
            }
        } catch (Exception e) {
            log.error("更新视频失败", e);
            throw new ServiceException("更新视频失败");
        }
    }

    @Override
    public MuseumInfoVO selectMuseumInfoDetailByMuseumId(Integer museumId) {
        return museumInfoMapper.selectMuseumInfoDetailByMuseumId(museumId);
    }

    @Override
    @Transactional
    @RateLimiter(key = "updateMuseumStatusByMuseumId",time = 10,count = 3)
    public String updateMuseumStatusByMuseumId(Integer museumId) {
        try{
            MuseumInfo museumInfo = museumInfoMapper.selectById(museumId);
            if (museumInfo == null){
                log.error("该纪念馆不存在");
                throw new ServiceException("该纪念馆不存在");
            }else{
                museumInfo.setIsPublic(museumInfo.getIsPublic()==0 ? 1 : museumInfo.getIsPublic());
                int updateResult = museumInfoMapper.updateById(museumInfo);
                if (updateResult<=0){
                    log.error("纪念馆ID:{},更新状态失败", museumId);
                    throw new ServiceException("纪念馆更新状态失败");
                }
                log.info("纪念馆ID:{},更新状态成功", museumId);
                return "更新状态成功";
            }
        }catch (Exception e){
            log.error("该纪念馆不存在 {}",e.getMessage());
            throw new ServiceException("该纪念馆不存在");
        }
    }
}
