package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Config;
import com.ruoyi.project.business.service.ConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Resource
    private ConfigService configService;

    @ApiOperation("查询平台协议")
    @GetMapping("/selectConfig")
    public R<List<Config>> selectConfig(){
        return R.ok(configService.list());
    }
    
    @ApiOperation("查询平台协议详情")
    @GetMapping("/selectConfigDetails")
    public R<Config> selectConfigDetails(@RequestParam Integer id) {
        return R.ok(configService.getById(id));
    }
}
