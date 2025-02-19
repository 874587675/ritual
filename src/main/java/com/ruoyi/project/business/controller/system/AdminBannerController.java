package com.ruoyi.project.business.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Banner;
import com.ruoyi.project.business.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-轮播图管理")
@RequestMapping("/system/banner")
public class AdminBannerController {
    @Resource
    private BannerService bannerService;

    // 获取轮播图列表
    @ApiOperation("获取轮播图列表")
    @GetMapping("/page")
    public R<IPage<Banner>> page(@RequestParam(defaultValue = "1") Integer pageNo,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(bannerService.page(new Page<>(pageNo, pageSize),new LambdaQueryWrapper<Banner>().orderByDesc(Banner::getUpdateTime)));
    }

    // 添加轮播图信息
    @ApiOperation("添加轮播图信息")
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody Banner banner){
        return R.ok(bannerService.save(banner));
    }

    //修改轮播图信息
    @ApiOperation("修改轮播图信息")
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody Banner banner){
        return R.ok(bannerService.updateById(banner));
    }

    // 删除轮播图信息
    @ApiOperation("删除轮播图信息")
    @PostMapping("/delete")
    public R<Boolean> delete(@RequestParam Integer id){
        return R.ok(bannerService.removeById(id));
    }
}
