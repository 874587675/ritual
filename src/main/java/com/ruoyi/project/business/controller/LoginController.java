package com.ruoyi.project.business.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.RegisterBody;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.UserService;
import com.ruoyi.project.business.util.aliyun.sms.SmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/index")
@Api(tags = "登录注册管理模块")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private SmsUtil smsUtil;

    @ApiOperation("用户名密码登录")
    @PostMapping("/loginByUsername")
    public AjaxResult loginByUsername(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = userService.loginByUsername(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());   // 生成令牌
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("手机号验证码登录")
    @PostMapping("/loginByPhone")
    public AjaxResult loginByPhone(@RequestBody LoginBody loginBody) throws ExecutionException, InterruptedException {
        AjaxResult ajax = AjaxResult.success();
        String token = userService.loginByPhone(loginBody.getPhone(), loginBody.getCode());// 生成令牌
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("获取手机验证码")
    @GetMapping("/getPhoneCode")
    public R<String> getPhoneCode(@RequestParam String phone) throws ExecutionException, InterruptedException {
        Boolean flag = smsUtil.SendPhoneCodeToLoginOrRegister(phone);
        if (flag) return R.ok("获取验证码成功");
        else return R.fail("获取验证码失败");
    }

    @ApiOperation("使用用户名密码注册")
    @PostMapping("/registerByUsername")
    public R<String> registerByUsername(@RequestBody RegisterBody registerBody) {
        return R.ok(userService.registerByUsername(registerBody.getUsername(), registerBody.getPassword(), registerBody.getCode(),
                registerBody.getUuid()));
    }

    @ApiOperation("使用手机号注册")
    @PostMapping("/registerByPhone")
    public R<String> registerByPhone(@RequestBody RegisterBody registerBody) {
        return R.ok(userService.registerByPhone(registerBody.getPhone(), registerBody.getPassword(), registerBody.getCode()));
    }

    @ApiOperation("使用邮箱注册")
    @PostMapping("/registerByEmail")
    public R<String> registerByEmail(@RequestBody RegisterBody registerBody) {
        return R.ok(userService.registerByEmail(registerBody.getEmail(), registerBody.getPassword(), registerBody.getCode()));
    }
    
    @ApiOperation("查询用户名是否已被使用")
    @GetMapping("/checkUsername")
    public R<Boolean> checkUsername(@RequestParam String username) {
        return R.ok(userService.checkUsername(username));
    }

    @ApiOperation("查询手机号是否已被使用")
    @GetMapping("/checkPhone")
    public R<Boolean> checkPhone(@RequestParam String phone) {return R.ok(userService.checkPhone(phone));}

    @ApiOperation("查询邮箱是否已被使用")
    @GetMapping("/checkEmail")
    public R<Boolean> checkEmail(@RequestParam String email) {return R.ok(userService.checkEmail(email));}
}
