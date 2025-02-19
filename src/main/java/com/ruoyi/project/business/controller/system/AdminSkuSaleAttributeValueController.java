package com.ruoyi.project.business.controller.system;

import com.ruoyi.project.business.service.SkuSaleAttributeValueService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-sku销售属性管理")
@RequestMapping("/system/skuSaleAttributeValue")
public class AdminSkuSaleAttributeValueController {

    @Resource
    private SkuSaleAttributeValueService skuSaleAttributeValueService;



}
