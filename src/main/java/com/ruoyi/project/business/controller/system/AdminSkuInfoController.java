package com.ruoyi.project.business.controller.system;

import com.ruoyi.project.business.service.SkuInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-SKU商品信息管理")
@RequestMapping("/system/skuInfo")
public class AdminSkuInfoController {
    @Resource
    private SkuInfoService skuInfoService;

    
}
