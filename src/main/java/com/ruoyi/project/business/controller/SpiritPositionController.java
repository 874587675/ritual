package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.SpiritPosition;
import com.ruoyi.project.business.service.SpiritPositionService;
import com.ruoyi.project.business.service.impl.SpiritPositionServiceImpl;
import com.ruoyi.project.business.vo.SpiritPositionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "祠堂馆灵位信息管理模块")
@RequestMapping("/spiritPosition")
public class SpiritPositionController {

    @Resource
    private SpiritPositionService spiritPositionService;

    @ApiOperation("查询该祠堂馆的所有灵位信息")
    @GetMapping("/selectSpiritPosition")
    public R<List<SpiritPosition>> selectSpiritPosition(@RequestParam Integer museumId){
        return R.ok(spiritPositionService.selectSpiritPositionByMuseumId(museumId));
    }
    
    @ApiOperation("添加该祠堂馆的灵位信息")
    @PostMapping("/insertSpiritPosition")
    public R<String> insertSpiritPosition(@RequestBody SpiritPosition spiritPosition){
        return R.ok(spiritPositionService.insertSpiritPosition(spiritPosition));
    }
    
    @ApiOperation("更新该祠堂馆的灵牌信息")
    @PostMapping("/updateSpiritPosition")
    public R<String> updateSpiritPosition(@RequestBody List<SpiritPositionVO> spiritPositionVOList){
        return R.ok(spiritPositionService.updateSpiritPosition(spiritPositionVOList));
    }
}
