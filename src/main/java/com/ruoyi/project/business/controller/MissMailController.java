package com.ruoyi.project.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MissMail;
import com.ruoyi.project.business.service.MissMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "思念有音信箱管理模块")
@RequestMapping("/missMail")
public class MissMailController {
    @Resource
    private MissMailService missMailService;

    @ApiOperation("选择星星输入想说的话")
    @PostMapping("/insertMissMail")
    public R<String> insertMissMail(@RequestBody MissMail missMail) {
        return R.ok(missMailService.insertMissMail(missMail));
    }

    @ApiOperation("回复思念有音的信箱信息")
    @PostMapping("/replyMissMail")
    public R<String> replyMissMail(@RequestBody MissMail missMail) {
        return R.ok(missMailService.replyMissMail(missMail));
    }

    @ApiOperation("分页查看回音信息")
    @GetMapping("/selectMissMailPage")
    public R<IPage<MissMail>> selectMissMailPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam Integer missId){
        return R.ok(missMailService.selectMissMailPage(pageNo, pageSize, missId));
    }
}
