package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.service.ItemOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "虚拟物品配置信息模块")
@RequestMapping("/itemOption")
public class ItemOptionController {

    @Resource
    private ItemOptionService itemOptionService;
    
}
