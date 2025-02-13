package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Room;
import com.ruoyi.project.business.domain.RoomBack;
import com.ruoyi.project.business.service.RoomService;
import com.ruoyi.project.business.vo.RoomVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "房间模块管理")
@RequestMapping("/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @ApiOperation("创建房间")
    @PostMapping("/createRoom")
    public R<RoomVO> createRoom(@RequestBody RoomVO roomVO){
        return R.ok(roomService.createRoom(roomVO));
    }
    
    @ApiOperation("关闭结束房间")
    @PostMapping("/closeRoom")
    public R<String> closeRoom(@RequestParam Integer id){
        return R.ok(roomService.closeRoom(id));
    }

    @ApiOperation("更换房间背景")
    @PostMapping("/changeRoomBack")
    public R<String> changeRoomBack(@RequestBody RoomBack roomBack){
        return R.ok(roomService.changeRoomBack(roomBack));
    }
    
    @ApiOperation("查询我发布的追思会房间")
    @GetMapping("/myRoom")
    public R<IPage<RoomVO>> myRoom(@RequestParam(defaultValue = "1") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam String userId){
        return R.ok(roomService.myRoom(pageNo,pageSize,userId));
    }
    
    
}
