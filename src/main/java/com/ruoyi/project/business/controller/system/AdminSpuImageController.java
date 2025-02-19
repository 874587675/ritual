package com.ruoyi.project.business.controller.system;

import com.ruoyi.project.business.service.SpuImageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-spu图片")
@RequestMapping("/system/spuImage")
public class AdminSpuImageController {

    @Resource
    private SpuImageService spuImageService;



}
