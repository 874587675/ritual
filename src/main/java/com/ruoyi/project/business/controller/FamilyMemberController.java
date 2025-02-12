package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.FamilyMember;
import com.ruoyi.project.business.service.FamilyMemberService;
import com.ruoyi.project.business.service.impl.FamilyMemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@RestController
@Api(tags = "亲友团成员管理")
@RequestMapping("/familyMember")
public class FamilyMemberController {

    @Resource
    private FamilyMemberService familyMemberService;

    @ApiOperation("查询该馆藏的亲友团成员")
    @GetMapping("/selectAllFamilyMembersByTeamId")
    public R<IPage<FamilyMember>> selectAllFamilyMembersByTeamId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam Long teamId) {
        return R.ok(familyMemberService.selectAllFamilyMembersByTeamId(pageNo, pageSize, teamId));
    }
    
    @ApiOperation("审核亲友团成员的状态")
    @PostMapping("/updateFamilyMemberStatus")
    public R<String> updateFamilyMemberStatus(@RequestParam Integer id, @RequestParam Integer status) {
        return R.ok(familyMemberService.updateFamilyMemberStatus(id,status));
    }
    
    @ApiOperation("转让亲友团的管理权")
    @PostMapping("/transFamilyMemberRole")
    public R<String> transFamilyMemberRole(@RequestParam Long teamId, @RequestParam Long oldUserId, @RequestParam Long newUserId) {
        return R.ok(familyMemberService.transFamilyMemberRole(teamId, oldUserId, newUserId));
    }
    
    @ApiOperation("移除亲友团成员操作")
    @PostMapping("/deleteFamilyMember")
    public R<String> deleteFamilyMember(@RequestParam Long id) {
        return R.ok(familyMemberService.deleteFamilyMember(id));
    }
    
    
}
