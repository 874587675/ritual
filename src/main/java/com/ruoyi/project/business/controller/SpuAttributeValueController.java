package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.SpuAttributeValue;
import com.ruoyi.project.business.service.SpuAttributeValueService;
import com.ruoyi.project.business.service.impl.SpuAttributeValueServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/spuAttributeValue")
public class SpuAttributeValueController {

    @Autowired
    private SpuAttributeValueService spuAttributeValueService;



}
