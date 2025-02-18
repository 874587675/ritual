package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.service.SkuInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "SKU商品信息管理")
@RequestMapping("/skuInfo")
public class SkuInfoController {
    @Resource
    private SkuInfoService skuInfoService;

    
}
