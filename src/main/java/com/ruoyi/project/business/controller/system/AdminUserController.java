package com.ruoyi.project.business.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-用户信息管理")
@RequestMapping("/system/user")
public class AdminUserController
{
    @Resource
    private UserService userService;

    @ApiOperation("查询所有的用户信息")
    @GetMapping("/page")
    public R<IPage<User>> page(@RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(userService.page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<User>().orderByAsc(User::getStatus).orderByDesc(User::getUpdateTime)));
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody User user) {
        return R.ok(userService.updateById(user));
    }

    @ApiOperation("维护用户状态（启用、停用）")
    @PostMapping("/status")
    public R<Boolean> status(@RequestParam String userId, @RequestParam Integer status) {
        return R.ok(userService.update(new LambdaUpdateWrapper<User>().eq(User::getId, userId).set(User::getStatus, status)));
    }
}
