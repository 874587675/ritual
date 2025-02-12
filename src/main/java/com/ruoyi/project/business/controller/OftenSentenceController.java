package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.domain.OftenSentence;
import com.ruoyi.project.business.service.OftenSentenceService;
import com.ruoyi.project.business.service.impl.OftenSentenceServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@RestController
@RequestMapping("/oftenSentence")
public class OftenSentenceController {

    @Resource
    private OftenSentenceService oftenSentenceService;
    

}
