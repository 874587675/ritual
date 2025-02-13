package com.ruoyi.project.business.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.Agency;
import com.ruoyi.project.business.mapper.AgencyMapper;
import com.ruoyi.project.business.service.AgencyService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class AgencyServiceImpl extends ServiceImpl<AgencyMapper, Agency> implements AgencyService{

    @Resource
    private AgencyMapper agencyMapper;

    @Override
    @Transactional
    public String insertAgencyInfo(Agency agency) {
        try{
            int insertResult = agencyMapper.insert(agency);
            if (insertResult<=0){
                log.error("添加代理商审核信息失败, 机构ID:{}", agency.getId());
                throw new RuntimeException("添加代理商审核信息失败");
            }
            log.info("添加代理商审核信息成功");
            return "添加代理商审核信息成功";
        }catch(Exception e){
            log.error("添加代理商审核信息失败, 机构ID:{}", agency.getId());
            throw new RuntimeException("添加代理商审核信息失败");
        }
    }
}
