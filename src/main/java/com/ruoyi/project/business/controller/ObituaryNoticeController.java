package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.ObituaryNotice;
import com.ruoyi.project.business.service.ObituaryNoticeService;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "讣告公告信息管理模块")
@RequestMapping("/obituaryNotice")
public class ObituaryNoticeController {

    @Resource
    private ObituaryNoticeService obituaryNoticeService;

    @ApiOperation("/分页查询自己发布的讣告")
    @GetMapping("/selectObituaryNoticeByUserId")
    public R<ObituaryNoticeVO> selectObituaryNoticeByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam String userId) {
        return R.ok(obituaryNoticeService.selectObituaryNoticeByUserId(pageNo, pageSize, userId));
    }
    
    @ApiOperation("删除自己发布的讣告")
    @PostMapping("/deleteObituaryNotice")
    public R<String> deleteObituaryNotice(@RequestParam Integer id){
        return R.ok(obituaryNoticeService.deleteObituaryNotice(id));
    }
}
