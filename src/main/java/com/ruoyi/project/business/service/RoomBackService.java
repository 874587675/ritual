package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.RoomBack;
import com.baomidou.mybatisplus.extension.service.IService;
public interface RoomBackService extends IService<RoomBack>{


    IPage<RoomBack> selectAllRoomBacks(Integer pageNum, Integer pageSize);

    
}
