package com.ruoyi.project.business.controller;

import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.LikeMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "消息点赞功能模块管理")
@RequestMapping("/likeMessage")
public class LikeMessageController {

    @Resource
    private LikeMessageService likeMessageService;

    @ApiOperation("对消息进行点赞或取消点赞操作")
    @PostMapping("/likeOrCancelLikeMessage")
    @RateLimiter(key="likeOrCancelLikeMessage", time = 10, count = 3)
    public R<String> likeOrCancelLikeMessage(@RequestParam Integer messageId){
        return R.ok(likeMessageService.likeOrCancelLikeMessage(messageId));
    }
}
