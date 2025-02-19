package com.ruoyi.project.business.controller;


import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Banner;
import com.ruoyi.project.business.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/banner")
@Api(tags = "轮播图管理模块")
public class BannerController {
    @Resource
    private BannerService bannerService;

    @ApiOperation("查询所有的轮播图信息")
    @GetMapping("/selectAllBannerInfo")
    public R<List<Banner>> selectAllBannerInfo(){
        return R.ok(bannerService.list());
    }


}
