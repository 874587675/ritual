package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MuseumSound;
import com.ruoyi.project.business.service.MuseumSoundService;
import com.ruoyi.project.business.service.impl.MuseumSoundServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "馆藏音乐管理模块")
@RequestMapping("/museumSound")
public class MuseumSoundController {

    @Resource
    private MuseumSoundService museumSoundService;

    @ApiOperation("查询馆藏的所有音乐信息")
    @GetMapping("/selectAllMuseumSound")
    public R<List<MuseumSound>> selectAllMuseumSound(){
        return R.ok(museumSoundService.selectAllMuseumSound());
    }
}
