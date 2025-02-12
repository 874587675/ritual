package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.service.SigninRewardsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/signinRewards")
public class SigninRewardsController {

    @Resource
    private SigninRewardsService signinRewardsService;


}
