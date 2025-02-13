package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.RoomBack;
import com.ruoyi.project.business.vo.RoomVO;

public interface RoomService extends IService<Room>{
    RoomVO createRoom(RoomVO roomVO);

    String changeRoomBack(RoomBack roomBack);

    String closeRoom(Integer id);

    IPage<RoomVO> myRoom(Integer pageNo, Integer pageSize, String userId);
}
