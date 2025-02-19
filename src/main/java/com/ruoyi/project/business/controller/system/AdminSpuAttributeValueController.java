package com.ruoyi.project.business.controller.system;

import com.ruoyi.project.business.service.SpuAttributeValueService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-spu属性值管理")
@RequestMapping("/system/spuAttributeValue")
public class AdminSpuAttributeValueController {

    @Resource
    private SpuAttributeValueService spuAttributeValueService;



}
