package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.SpuComment;
import com.ruoyi.project.business.service.SpuCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "商品评价信息管理模块")
@RequestMapping("/spuComment")
public class SpuCommentController {

    @Resource
    private SpuCommentService spuCommentService;

    @ApiOperation("分页查询出所有关于该商品的评价")
    @GetMapping("/selectSpuComment")
    public R<IPage<SpuComment>> selectSpuComment(@RequestParam(defaultValue = "1") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam Integer spuId) {
        return R.ok(spuCommentService.selectSpuComment(pageNo, pageSize, spuId));
    }
    
    @ApiOperation("对商城商品做出评价")
    @PostMapping("/insertSpuComment")
    public R<String> insertSpuComment(@RequestBody SpuComment spuComment){
        return R.ok(spuCommentService.insertSpuComment(spuComment));
    }
}
