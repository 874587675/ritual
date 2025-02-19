package com.ruoyi.project.business.controller.system;

import com.ruoyi.project.business.service.SkuImagesService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-SKU图片")
@RequestMapping("/system/skuImages")
public class AdminSkuImagesController {

    @Resource
    private SkuImagesService skuImagesService;
    
}
