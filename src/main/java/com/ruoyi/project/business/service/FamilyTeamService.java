package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.FamilyTeam;
import com.baomidou.mybatisplus.extension.service.IService;
public interface FamilyTeamService extends IService<FamilyTeam>{
    
    String generateFamilyQRCode(Integer teamId,String userId);
}
