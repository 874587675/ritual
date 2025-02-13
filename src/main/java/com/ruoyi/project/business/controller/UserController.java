package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;  


@RestController
@RequestMapping("/user")
@Api(tags = "用户基本信息管理模块")
public class UserController {
    @Resource
    private UserService userService;
    
    @ApiOperation("查询当前用户的信息")
    @GetMapping("/selectUserInfoByUserId")
    public R<User> selectUserInfoByUserId(@RequestParam("userId") String userId) {
        return R.ok(userService.getById(userId));
    }
    
    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserInfoByUserId")
    public R<String> updateUserInfoByUserId(@RequestBody User user) {
        return R.ok(userService.updateUserInfoByUserId(user));
    }
    
    @ApiOperation("个人生成邀请码赚取佣金")
    @PostMapping("/generateShareQRCode")
    public R<String> generateShareQRCode(@RequestParam("userId") String userId) {
        return R.ok(userService.generateShareQRCode(userId));
    }
}
