package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.Agency;
import com.ruoyi.project.business.mapper.AgencyMapper;
import com.ruoyi.project.business.service.AgencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    @Override
    public Boolean audit(Integer id, Integer status) {
        // 查询对应代理商的信息
        Agency agency = agencyMapper.selectById(id);
        if (agency == null){
            log.error("查询代理商信息失败, 机构ID:{}", id);
            throw new RuntimeException("查询代理商信息失败");
        }
        // 若状态为通过，则将机构状态置为正常
        if (status == 1){
            agency.setStatus(status);
        }
        // 若状态为拒绝，则将机构状态置为拒绝
        else if (status == 2){
            agency.setStatus(2);
        }
        else {
            log.error("状态参数不合法, 机构ID:{}", id);
            throw new RuntimeException("状态参数不合法");
        }
        // 若修改成功，返回true
        agencyMapper.updateById(agency);
        return true;
    }
}
