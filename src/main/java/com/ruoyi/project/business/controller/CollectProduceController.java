package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.CollectProduce;
import com.ruoyi.project.business.service.CollectProduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "收藏商品信息管理模块")
@RequestMapping("/collectProduce")
public class CollectProduceController {

    @Resource
    private CollectProduceService collectProduceService;

    @ApiOperation("查询用户收藏的商品")
    @GetMapping("/collectProduceByUserId")
    public R<IPage<CollectProduce>> collectProduceByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam String userId) {
        return R.ok(collectProduceService.collectProduceByUserId(pageNo, pageSize, userId));
    }

    @ApiOperation("用户收藏或取消商品收藏")
    @GetMapping("/addOrCancelCollectProduce")
    public R<String> addOrCancelCollectProduce(@RequestParam String userId,
                                                @RequestParam Integer spuId) {
        return R.ok(collectProduceService.addOrCancelCollectProduce(userId, spuId));
    }
}
