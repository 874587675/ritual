package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.SkuInfo;
import com.ruoyi.project.business.domain.SpuInfo;
import com.ruoyi.project.business.service.SpuInfoService;
import com.ruoyi.project.business.service.impl.SpuInfoServiceImpl;
import com.ruoyi.project.business.vo.SpuInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "商品SPU信息管理模块")
@RequestMapping("/spuInfo")
public class SpuInfoController {
    @Resource
    private SpuInfoService spuInfoService;
    @ApiOperation("分页查询商品SPU信息")
    @GetMapping("/selectSpuInfoPage")
    public R<IPage<SpuInfoVO>> selectSpuInfoPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(spuInfoService.selectSpuInfoPage(pageNo, pageSize));
    }

    @ApiOperation("分页查询热销商品SPU信息")
    @GetMapping("/selectHotSpuInfoPage")
    public R<IPage<SpuInfoVO>> selectHotSpuInfoPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(spuInfoService.selectHotSpuInfoPage(pageNo, pageSize));
    }
    
    @ApiOperation("查询商品具体详情")
    @GetMapping("/selectSpuInfoById")
    public R<SpuInfoVO> selectSpuInfoById(@RequestParam Integer spuId) {
        return R.ok(spuInfoService.selectSpuInfoById(spuId));
    }

    @ApiOperation("查询出所有该商品的规格信息")
    @GetMapping("/selectAllSkuInfo")
    public R<SpuInfoVO> selectAllSkuInfo(@RequestParam Integer spuId){
        return R.ok(spuInfoService.selectAllSkuInfo(spuId));
    }
}
