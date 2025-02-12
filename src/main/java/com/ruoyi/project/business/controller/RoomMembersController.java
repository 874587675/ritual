package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.RoomMembers;
import com.ruoyi.project.business.service.RoomMembersService;
import com.ruoyi.project.business.vo.RoomMembersVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Api(tags = "房间成员模块管理")
@RequestMapping("/roomMembers")
public class RoomMembersController {
    @Resource
    private RoomMembersService roomMembersService;

    @ApiOperation("查询房间内所有的成员")
    @GetMapping("/getRoomMembers")
    public R<IPage<RoomMembers>> getRoomMembers(@RequestParam(defaultValue = "1") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam Integer roomId){
        return R.ok(roomMembersService.getRoomMembers(pageNo,pageSize,roomId));
    }
    
    @ApiOperation("新增房间成员")
    @PostMapping("/addRoomMembers")
    public R<String> addRoomMembers(@RequestBody RoomMembersVO roomMembersVO){
        return R.ok(roomMembersService.addRoomMembers(roomMembersVO));
    }
    
    @ApiOperation("删除房间成员(逻辑删除)")
    @PostMapping("/deleteRoomMembers")
    public R<String> deleteRoomMembers(@RequestParam Integer id){
        return R.ok(roomMembersService.deleteRoomMembers(id));
    }
    
    
}
