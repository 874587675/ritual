package com.ruoyi.project.business.controller;

import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.UserOption;
import com.ruoyi.project.business.service.UserOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "用户投诉与建议信息管理模块")
@RequestMapping("/userOption")
public class UserOptionController {

    @Resource
    private UserOptionService userOptionService;

    @ApiOperation("用户新增投诉与建议信息")
    @PostMapping("/insertUserOption")
    @RateLimiter(key = "insertUserOption", time = 20, count = 3)
    public R<String> insertUserOption(@RequestBody UserOption userOption){
        return R.ok(userOptionService.insertUserOption(userOption));
    }
}
