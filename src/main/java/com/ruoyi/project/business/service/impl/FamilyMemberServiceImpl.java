package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.FamilyTeam;
import com.ruoyi.project.business.mapper.FamilyTeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.FamilyMember;
import com.ruoyi.project.business.mapper.FamilyMemberMapper;
import com.ruoyi.project.business.service.FamilyMemberService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class FamilyMemberServiceImpl extends ServiceImpl<FamilyMemberMapper, FamilyMember> implements FamilyMemberService{

    @Resource
    private FamilyTeamMapper familyTeamMapper;
    @Resource
    private FamilyMemberMapper familyMemberMapper;

    @Override
    public IPage<FamilyMember> selectAllFamilyMembersByTeamId(Integer pageNo, Integer pageSize, Integer teamId) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<FamilyMember>().eq(FamilyMember::getTeamId,teamId).orderByDesc(FamilyMember::getCreateTime));
    }

    @Override
    public String updateFamilyMemberStatus(Integer id, Integer status) {
        try{
            FamilyMember familyMember = familyMemberMapper.selectById(id);
            if(familyMember!=null){
                int updateResult = familyMemberMapper.update(new LambdaUpdateWrapper<FamilyMember>().set(FamilyMember::getJoinStatus, status).eq(FamilyMember::getId, id));
                if (updateResult<=0){
                    log.error("审核亲友团成员操作失败");
                    throw new ServiceException("审核亲友团成员操作失败");
                }else {
                    log.info("审核亲友团成员操作成功");
                    return "修改成功";
                }
            }else{
                log.error("找不到此用户成员");
                throw new ServiceException("找不到此用户成员");
            }
        }catch (Exception e){
            log.error("审核亲友团成员操作失败");
            throw new ServiceException("审核亲友团成员操作失败");
        }
    }

    @Override
    @Transactional
    public String transFamilyMemberRole(Integer teamId, Integer oldUserId, Integer newUserId) {
        try{
            FamilyTeam familyTeam = familyTeamMapper.selectById(teamId);
            if (familyTeam == null){
                log.error("找不到此亲友团");
                throw new ServiceException("找不到此亲友团");
            }else{
                FamilyMember oldFamilyMember = familyMemberMapper.selectOne(new LambdaQueryWrapper<FamilyMember>().eq(FamilyMember::getUserId, oldUserId).eq(FamilyMember::getTeamId, teamId));
                FamilyMember newFamilyMember = familyMemberMapper.selectOne(new LambdaQueryWrapper<FamilyMember>().eq(FamilyMember::getUserId, newUserId).eq(FamilyMember::getTeamId, teamId));
                if (oldFamilyMember == null || newFamilyMember == null){
                    log.error("找不到此用户或者此用户不在此亲友团中");
                    throw new ServiceException("找不到此用户或者此用户不在此亲友团中");
                }
                if (oldFamilyMember.getRole()!=1){
                    log.error("只有管理员权限才能转移");
                    throw new ServiceException("只有管理员权限才能转移");
                }
                int updateOldUserResult = familyMemberMapper.update(new LambdaUpdateWrapper<FamilyMember>().set(FamilyMember::getRole, newFamilyMember.getRole()).eq(FamilyMember::getId, oldFamilyMember.getId()));
                int updateNewUserResult = familyMemberMapper.update(new LambdaUpdateWrapper<FamilyMember>().set(FamilyMember::getRole, oldFamilyMember.getRole()).eq(FamilyMember::getId, newFamilyMember.getId()));
                if (updateOldUserResult<=0 || updateNewUserResult<=0){
                    log.error("转移管理权操作失败");
                    throw new ServiceException("转移管理权操作失败");
                }
                log.info("转移管理权操作成功");
                return "转移管理权成功";
            }
        }catch (Exception e){
            log.error("转移管理权操作失败");
            throw new ServiceException("转移管理权操作失败");
        }
    }

    @Override
    @Transactional
    public String deleteFamilyMember(Integer id) {
        try{
            FamilyMember familyMember = familyMemberMapper.selectById(id);
            if(familyMember!=null){
                int deleteResult = familyMemberMapper.deleteById(id);
                if (deleteResult<=0){
                    log.error("删除亲友团成员操作失败");
                    throw new ServiceException("删除亲友团成员操作失败");
                }else {
                    log.info("删除亲友团成员操作成功");
                    return "删除成功";
                }
            }else{
                log.error("找不到此用户成员");
                throw new ServiceException("找不到此用户成员");
            }
        }catch (Exception e){
            log.error("删除亲友团成员操作失败");
            throw new ServiceException("删除亲友团成员操作失败");
        }
    }
}
