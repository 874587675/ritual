package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MuseumBack;
import com.ruoyi.project.business.service.MuseumBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-馆藏背景管理")
@RequestMapping("/system/museumBack")
public class AdminMuseumBackController {

    @Resource
    private MuseumBackService museumBackService;

    @ApiOperation("分页查询所有的纪念馆背景")
    @GetMapping("/page")
    public R<IPage<MuseumBack>> page(@RequestParam(defaultValue = "1") Integer pageNo,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(museumBackService.page(new Page<>(pageNo,pageSize)));
    }

    //添加纪念馆背景信息
    @ApiOperation("添加纪念馆背景")
    @PostMapping("/add")
    public R<Boolean> add(@RequestBody MuseumBack museumBack){
        return R.ok(museumBackService.save(museumBack));
    }

    //修改纪念馆背景信息
    @ApiOperation("修改纪念馆背景")
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody MuseumBack museumBack){
        return R.ok(museumBackService.updateById(museumBack));
    }

    //删除纪念馆背景
    @ApiOperation("删除纪念馆背景")
    @PostMapping("/delete")
    public R<Boolean> delete(@RequestParam Integer id){
        return R.ok(museumBackService.removeById(id));
    }
}
