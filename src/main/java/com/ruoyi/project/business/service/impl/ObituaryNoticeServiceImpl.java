package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.ObituaryNotice;
import com.ruoyi.project.business.mapper.ObituaryNoticeMapper;
import com.ruoyi.project.business.service.ObituaryNoticeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class ObituaryNoticeServiceImpl extends ServiceImpl<ObituaryNoticeMapper, ObituaryNotice> implements ObituaryNoticeService {
    @Resource
    private ObituaryNoticeMapper obituaryNoticeMapper;

    @Override
    public ObituaryNoticeVO selectObituaryNoticeByUserId(Integer pageNo, Integer pageSize, String userId) {
        return obituaryNoticeMapper.selectObituaryNoticeByUserId(new Page<>(pageNo, pageSize), userId);
    }

    @Override
    @Transactional
    public String deleteObituaryNotice(Integer id) {
        try {
            int deleteResult = obituaryNoticeMapper.deleteById(id);
            if (deleteResult <= 0) {
                log.error("删除讣告失败");
                throw new ServiceException("删除讣告失败");
            } else {
                log.info("删除讣告成功");
                return "删除讣告成功";
            }
        } catch (Exception e) {
            log.error("删除讣告失败 {}", e.getMessage());
            throw new ServiceException("删除讣告失败");
        }
    }
}
