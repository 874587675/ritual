package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.ObituaryBack;
import com.ruoyi.project.business.service.ObituaryBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "讣告背景模板管理")
@RequestMapping("/obituaryBack")
public class ObituaryBackController {

    @Resource
    private ObituaryBackService obituaryBackService;

    @ApiOperation("分页查询出所有的图片讣告模板")
    @GetMapping("/selectObituaryBackForImage")
    public R<IPage<ObituaryBack>> selectObituaryBackForImage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                             @RequestParam(defaultValue = "30") Integer pageSize){
        return R.ok(obituaryBackService.selectObituaryBackForImage(pageNo, pageSize));
    }

    @ApiOperation("分页查询出所有的文字讣告模板")
    @GetMapping("/selectObituaryBackForContent")
    public R<IPage<ObituaryBack>> selectObituaryBackForContent(@RequestParam(defaultValue = "1") Integer pageNo,
                                                             @RequestParam(defaultValue = "30") Integer pageSize){
        return R.ok(obituaryBackService.selectObituaryBackForContent(pageNo, pageSize));
    }
        
}
