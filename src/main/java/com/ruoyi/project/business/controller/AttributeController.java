package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.Attribute;
import com.ruoyi.project.business.service.AttributeService;
import com.ruoyi.project.business.service.impl.AttributeServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;



}
