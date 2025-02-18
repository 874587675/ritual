package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.domain.Coupon;
import com.ruoyi.project.business.service.CouponService;
import com.ruoyi.project.business.service.impl.CouponServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@RestController
@Api(tags = "优惠券信息管理模块")
@RequestMapping("/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;


}
