package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.service.BalanceChangeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "余额变动信息管理模块")
@RequestMapping("/balanceChange")
public class BalanceChangeController {

    @Resource
    private BalanceChangeService balanceChangeService;


}
