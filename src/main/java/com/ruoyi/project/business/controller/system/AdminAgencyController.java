package com.ruoyi.project.business.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.interceptor.annotation.RepeatSubmit;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Agency;
import com.ruoyi.project.business.service.AgencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-代理管理")
@RequestMapping("/system/agency")
public class AdminAgencyController {

    @Resource
    private AgencyService agencyService;

    @ApiOperation("审核代理商状态")
    @GetMapping("/audit")
    public R<Boolean> audit(@RequestParam Integer id,@RequestParam Integer status){
        return R.ok(agencyService.audit(id,status));
    }

    @ApiOperation("获取代理商列表")
    @GetMapping("/list")
    public R<IPage<Agency>> list(@RequestParam(defaultValue = "1") Integer pageNo,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(agencyService.page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<Agency>().orderByAsc(Agency::getStatus).orderByDesc(Agency::getUpdateTime)));
    }

    @ApiOperation("后台新增代理商信息")
    @PostMapping("/insertAgencyInfo")
    @RepeatSubmit
    public R<String> insertAgencyInfo(@RequestBody Agency agency){
        return R.ok(agencyService.insertAgencyInfo(agency));
    }
}
