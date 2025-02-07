package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.SpuImage;
import com.ruoyi.project.business.service.SpuImageService;
import com.ruoyi.project.business.service.impl.SpuImageServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/spuImage")
public class SpuImageController {

    @Autowired
    private SpuImageService spuImageService;



}
