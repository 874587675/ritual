package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.CollectMuseum;
import com.ruoyi.project.business.service.CollectMuseumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "收藏馆藏信息管理模块")
@RequestMapping("/collectMuseum")
public class CollectMuseumController {

    @Resource
    private CollectMuseumService collectMuseumService;

    @GetMapping("/selectCollectMuseumPageByUserId")
    @ApiOperation("分页查询用户收藏的馆藏信息")
    public R<IPage<CollectMuseum>> selectCollectMuseumPageByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                                   @RequestParam String userId) {
        return R.ok(collectMuseumService.selectCollectMuseumPageByUserId(pageNo, pageSize, userId));
    }
    
    @ApiOperation("用户添加或取消关注馆藏信息")
    @GetMapping("/addOrCancelCollectMuseum")
    public R<Boolean> addOrCancelCollectMuseum(@RequestParam String userId, 
                                      @RequestParam Integer museumId) {
        return R.ok(collectMuseumService.addOrCancelCollectMuseum(userId, museumId));
    }
    
}
