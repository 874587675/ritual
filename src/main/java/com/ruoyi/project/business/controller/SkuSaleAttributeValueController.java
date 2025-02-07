package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.SkuSaleAttributeValue;
import com.ruoyi.project.business.service.SkuSaleAttributeValueService;
import com.ruoyi.project.business.service.impl.SkuSaleAttributeValueServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/skuSaleAttributeValue")
public class SkuSaleAttributeValueController {

    @Autowired
    private SkuSaleAttributeValueService skuSaleAttributeValueService;



}
