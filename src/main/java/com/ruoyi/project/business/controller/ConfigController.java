package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.Config;
import com.ruoyi.project.business.service.ConfigService;
import com.ruoyi.project.business.service.impl.ConfigServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/config")
public class ConfigController {

@Autowired
private ConfigService configService;


}
