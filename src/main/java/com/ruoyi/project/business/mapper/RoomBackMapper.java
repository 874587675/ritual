package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.Room;
import com.ruoyi.project.business.domain.RoomBack;
import com.ruoyi.project.business.vo.RoomVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomBackMapper extends BaseMapper<RoomBack> {
    
}
