package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.SigninRewards;
import com.ruoyi.project.business.service.SigninRewardsService;
import com.ruoyi.project.business.vo.SigninRewardsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "签到奖励信息管理")
@RequestMapping("/signinRewards")
public class SigninRewardsController {

    @Resource
    private SigninRewardsService signinRewardsService;

    @ApiOperation("检查用户今天签到状态")
    @GetMapping("/checkTodaySignin")
    public R<SigninRewardsVO> checkTodaySignin(@RequestParam String userId) {
        return R.ok(signinRewardsService.checkTodaySignin(userId));
    }

    @ApiOperation("用户签到操作")
    @PostMapping("/signin")
    public R<String> signin(@RequestParam String userId) {
        return R.ok(signinRewardsService.signin(userId));
    }

    @ApiOperation("查询出签到奖励表的内容")
    @GetMapping("/listSigninRewards")
    public R<List<SigninRewards>> listSigninRewards() {
        return R.ok(signinRewardsService.list());
    }
}
