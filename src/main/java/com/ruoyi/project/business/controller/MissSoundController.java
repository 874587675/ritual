package com.ruoyi.project.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MissSound;
import com.ruoyi.project.business.service.MissSoundService;
import com.ruoyi.project.business.vo.MissSoundVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "思念有音信息管理模块")
@RequestMapping("/missSound")
public class MissSoundController {
    @Resource
    private MissSoundService missSoundService;


    @ApiOperation("创建守护星")
    @PostMapping("/createStar")
    public R<String> createStar(@RequestBody MissSoundVO missSoundVO) {
        return R.ok(missSoundService.createStar(missSoundVO));
    }

    @ApiOperation("查询我创建的守护星")
    @GetMapping("/myStars")
    public R<IPage<MissSound>> myStars(@RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam Integer userId) {
        return R.ok(missSoundService.myStars(pageNo, pageSize, userId));
    }
}
