package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.SpuInfo;
import com.ruoyi.project.business.service.SpuInfoService;
import com.ruoyi.project.business.service.impl.SpuInfoServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/spuInfo")
public class SpuInfoController {

    @Autowired
    private SpuInfoService spuInfoService;



}
