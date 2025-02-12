package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.FamilyMember;
import com.baomidou.mybatisplus.extension.service.IService;
public interface FamilyMemberService extends IService<FamilyMember>{
    
    IPage<FamilyMember> selectAllFamilyMembersByTeamId(Integer pageNo, Integer pageSize, Long teamId);

    String updateFamilyMemberStatus(Integer id, Integer status);

    String transFamilyMemberRole(Long teamId, Long oldUserId, Long newUserId);

    String deleteFamilyMember(Long id);
}
