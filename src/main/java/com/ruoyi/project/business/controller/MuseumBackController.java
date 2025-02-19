package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MuseumBack;
import com.ruoyi.project.business.service.MuseumBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "馆藏背景管理")
@RequestMapping("/museumBack")
public class MuseumBackController {

    @Resource
    private MuseumBackService museumBackService;

    @ApiOperation("分页查询所有的馆藏背景")
    @GetMapping("/selectAllMuseumBack")
    public R<IPage<MuseumBack>> selectAllMuseumBack(@RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(museumBackService.selectAllMuseumBack(pageNo,pageSize));
    }
}
