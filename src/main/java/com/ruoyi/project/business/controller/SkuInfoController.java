package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.SkuInfo;
import com.ruoyi.project.business.service.SkuInfoService;
import com.ruoyi.project.business.service.impl.SkuInfoServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/skuInfo")
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;



}
