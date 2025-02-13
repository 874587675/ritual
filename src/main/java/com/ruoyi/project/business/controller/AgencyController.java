package com.ruoyi.project.business.controller;

import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Agency;
import com.ruoyi.project.business.service.AgencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "城市代理商信息管理模块")
@RequestMapping("/agency")
public class AgencyController {

    @Resource
    private AgencyService agencyService;

    @ApiOperation("提交成为代理商的信息")
    @PostMapping("/insertAgencyInfo")
    @RateLimiter(key = "insertAgencyInfo", time = 5, count = 1)
    public R<String> insertAgencyInfo(@RequestBody Agency agency){
        return R.ok(agencyService.insertAgencyInfo(agency));
    }
}
