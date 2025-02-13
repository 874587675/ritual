package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.service.MuseumWatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "纪念馆关注信息管理")
@RequestMapping("/museumWatch")
public class MuseumWatchController {

    @Resource
    private MuseumWatchService museumWatchService;

    
}
