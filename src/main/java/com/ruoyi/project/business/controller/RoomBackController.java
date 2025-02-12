package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.RoomBack;
import com.ruoyi.project.business.service.RoomBackService;
import com.ruoyi.project.business.service.impl.RoomBackServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@RestController
@Api(tags = "房间背景模块管理")
@RequestMapping("/roomBack")
public class RoomBackController {

    @Resource
    private RoomBackService roomBackService;

    @ApiOperation("分页查询所有房间背景")
    @GetMapping("/selectAllRoomBacks")
    public R<IPage<RoomBack>> selectAllRoomBacks(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(roomBackService.selectAllRoomBacks(pageNum,pageSize));
    }
    
    

}
