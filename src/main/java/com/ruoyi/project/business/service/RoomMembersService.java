package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.RoomMembers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.RoomMembersVO;
import org.springframework.web.bind.annotation.RequestParam;

public interface RoomMembersService extends IService<RoomMembers>{
    String addRoomMembers(RoomMembersVO roomMembersVO);

    String deleteRoomMembers(Integer id);

    IPage<RoomMembers> getRoomMembers(Integer pageNo,Integer pageSize,Integer roomId);
}
