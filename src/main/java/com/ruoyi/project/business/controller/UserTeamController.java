package com.ruoyi.project.business.controller;

import cn.hutool.system.UserInfo;
import com.alipay.api.domain.UserInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.UserTeamService;
import com.ruoyi.project.business.vo.UserTeamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "用户团队信息管理模块")
@RequestMapping("/userTeam")
public class UserTeamController {

    @Resource
    private UserTeamService userTeamService;

    @ApiOperation("分页查询个人用户团队收益信息")
    @GetMapping("/selectUserTeam")
    public R<IPage<UserTeamVO>> selectUserTeam(@RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam String userId) {
        return R.ok(userTeamService.selectUserTeam(pageNo, pageSize, userId));
    }
    
    @ApiOperation("根据用户ID查询团队的收益和总邀请人数")
    @GetMapping("/selectForIncomeAndInviteUserCount")
    public R<UserTeamVO> selectForIncomeAndInviteUserCount(@RequestParam String userId) {
        return R.ok(userTeamService.selectForIncomeAndInviteUserCount(userId));
    }
    
    

}
