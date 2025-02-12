package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.FamilyTeam;
import com.ruoyi.project.business.service.FamilyTeamService;
import com.ruoyi.project.business.service.impl.FamilyTeamServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@RestController
@Api(tags = "亲友团管理")
@RequestMapping("/familyTeam")
public class FamilyTeamController {

    @Resource
    private FamilyTeamService familyTeamService;

    @ApiOperation("亲友团生成分享二维码")
    @PostMapping("generateFamilyQRCode")
    public R<String>  generateFamilyQRCode(@RequestParam Integer teamId,@RequestParam String userId){
        return R.ok(familyTeamService.generateFamilyQRCode(teamId,userId));
    }
}
