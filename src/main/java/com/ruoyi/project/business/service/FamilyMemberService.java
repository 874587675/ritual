package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.FamilyMember;
import com.baomidou.mybatisplus.extension.service.IService;
public interface FamilyMemberService extends IService<FamilyMember>{
    
    IPage<FamilyMember> selectAllFamilyMembersByTeamId(Integer pageNo, Integer pageSize, Integer teamId);

    String updateFamilyMemberStatus(Integer id, Integer status);

    String transFamilyMemberRole(Integer teamId, Integer oldUserId, Integer newUserId);

    String deleteFamilyMember(Integer id);
}
