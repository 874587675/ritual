package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.CouponUser;
import com.ruoyi.project.business.service.CouponUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;


@RestController
@Api(tags = "用户领取优惠券信息模块管理")
@RequestMapping("/couponUser")
public class CouponUserController {

    @Resource
    private CouponUserService couponUserService;

    @ApiOperation("分页查询用户的所有的优惠券")
    @GetMapping("/selectCouponUser")
    public R<IPage<CouponUser>> selectCouponUser(@RequestParam(defaultValue = "1") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam String userId,
                                                 @RequestParam(required = false) Integer status){
        return R.ok(couponUserService.selectCouponUser(pageNo, pageSize, userId,status));
    }
    
    
    
}
