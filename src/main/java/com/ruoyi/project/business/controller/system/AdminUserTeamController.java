package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.UserTeamService;
import com.ruoyi.project.business.vo.UserTeamVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-用户推广团队管理")
@RequestMapping("/system/userTeam")
public class AdminUserTeamController {

    @Resource
    private UserTeamService userTeamService;

    @ApiOperation("分页查询出用户的推广团队")
    @GetMapping("/page")
    public R<IPage<UserTeamVO>> page(@RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(userTeamService.pageList(pageNo, pageSize));
    }


    @ApiOperation("分页查询个人用户团队收益信息")
    @GetMapping("/selectUserTeam")
    public R<IPage<UserTeamVO>> selectUserTeam(@RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam String userId) {
        return R.ok(userTeamService.selectUserTeam(pageNo, pageSize, userId));
    }

}
