package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MuseumBack;
import com.ruoyi.project.business.service.MuseumBackService;
import com.ruoyi.project.business.service.impl.MuseumBackServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "馆藏背景管理")
@RequestMapping("/museumBack")
public class MuseumBackController {

    @Resource
    private MuseumBackService museumBackService;

    @ApiOperation("查询所有的馆藏背景")
    @GetMapping("/selectAllMuseumBack")
    public R<List<MuseumBack>> selectAllMuseumBack(){
        return R.ok(museumBackService.selectAllMuseumBack());
    }
}
