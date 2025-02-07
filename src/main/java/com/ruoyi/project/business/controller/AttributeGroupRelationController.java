package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.AttributeGroupRelation;
import com.ruoyi.project.business.service.AttributeGroupRelationService;
import com.ruoyi.project.business.service.impl.AttributeGroupRelationServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/attributeGroupRelation")
public class AttributeGroupRelationController {

    @Autowired
    private AttributeGroupRelationService attributeGroupRelationService;



}
