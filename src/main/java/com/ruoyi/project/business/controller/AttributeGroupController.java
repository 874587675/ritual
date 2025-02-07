package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.AttributeGroup;
import com.ruoyi.project.business.service.AttributeGroupService;
import com.ruoyi.project.business.service.impl.AttributeGroupServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/attributeGroup")
public class AttributeGroupController {

    @Autowired
    private AttributeGroupService attributeGroupService;



}
