package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.RoomBackMapper;
import com.ruoyi.project.business.domain.RoomBack;
import com.ruoyi.project.business.service.RoomBackService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class RoomBackServiceImpl extends ServiceImpl<RoomBackMapper, RoomBack> implements RoomBackService{
    @Resource
    private RoomBackMapper roomBackMapper;

    @Override
    public IPage<RoomBack> selectAllRoomBacks(Integer pageNum, Integer pageSize) {
        return page(new Page<>(pageNum,pageSize));
    }

    
}
