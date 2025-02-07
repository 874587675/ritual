package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.SkuImages;
import com.ruoyi.project.business.service.SkuImagesService;
import com.ruoyi.project.business.service.impl.SkuImagesServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/skuImages")
public class SkuImagesController {

    @Autowired
    private SkuImagesService skuImagesService;
    
}
