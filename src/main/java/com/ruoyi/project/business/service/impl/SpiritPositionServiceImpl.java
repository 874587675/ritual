package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.mapper.MuseumInfoMapper;
import com.ruoyi.project.business.vo.SpiritPositionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.SpiritPosition;
import com.ruoyi.project.business.mapper.SpiritPositionMapper;
import com.ruoyi.project.business.service.SpiritPositionService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class SpiritPositionServiceImpl extends ServiceImpl<SpiritPositionMapper, SpiritPosition> implements SpiritPositionService {

    @Resource
    private SpiritPositionMapper spiritPositionMapper;
    @Resource
    private MuseumInfoMapper museumInfoMapper;

    @Override
    public List<SpiritPosition> selectSpiritPositionByMuseumId(Integer museumId) {
        MuseumInfo museumInfo = museumInfoMapper.selectById(museumId);
        if (museumInfo == null) {
            log.error("该祠堂馆不存在");
            throw new ServiceException("该祠堂馆不存在");
        }
        log.info("祠堂馆查询成功");
        return spiritPositionMapper.selectList(new LambdaQueryWrapper<SpiritPosition>().eq(SpiritPosition::getMuseumId, museumId));
    }

    @Override
    @Transactional
    public String insertSpiritPosition(SpiritPosition spiritPosition) {
        try {
            int insertResult = spiritPositionMapper.insert(spiritPosition);
            if (insertResult <= 0) {
                log.error("添加灵位信息失败, 祠堂藏ID:{}", spiritPosition.getMuseumId());
                throw new ServiceException("添加灵位信息失败");
            } else {
                log.info("添加灵位信息成功");
                return "添加灵位信息成功";
            }
        } catch (Exception e) {
            log.error("添加灵位信息失败{}", e.getMessage());
            throw new ServiceException("添加灵位信息失败");
        }
    }

    @Override
    @Transactional
    public String updateSpiritPosition(List<SpiritPositionVO> spiritPositionVOList) {
        try {
            //批量删除掉原先的灵牌
            if (!spiritPositionVOList.isEmpty()) {
                spiritPositionMapper.deleteById(spiritPositionVOList.get(0).getOldMuseumId());
                log.info("删除该祠堂管灵牌信息成功");
                // 将 SpiritPositionVO 转换成 SpiritPosition
                List<SpiritPosition> spiritPositionList = new ArrayList<>(spiritPositionVOList);
                // 批量插入新的灵牌信息
                boolean insertResult = saveBatch(spiritPositionList);
                if (insertResult) {
                    log.info("批量更新灵牌信息成功");
                    return "批量更新灵牌信息成功";
                } else {
                    log.error("批量更新灵牌信息失败");
                    throw new ServiceException("批量更新灵牌信息失败");
                }
            }
            log.error("该祠堂馆主键ID信息为空, 请提供需要更新的灵位信息");
            throw new ServiceException("该祠堂馆主键ID信息为空, 请提供需要更新的灵位信息");
        } catch (Exception e) {
            log.error("批量更新灵牌信息失败{}", e.getMessage());
            throw new ServiceException("批量更新灵牌信息失败");
        }
    }
    
    
}
