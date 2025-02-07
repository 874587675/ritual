package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;  


@RestController
@RequestMapping("/user")
@Api(tags = "用户基本信息管理模块")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    public R<Boolean> addUser(@RequestBody User user) {
        return R.ok(userService.save(user));
    }
}
