package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.UserReal;
import com.ruoyi.project.business.service.UserRealService;
import com.ruoyi.project.business.service.impl.UserRealServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/userReal")
@Api(tags = "用户真实信息管理模块")
public class UserRealController {

    @Autowired
    private UserRealService userRealService;
    
}
