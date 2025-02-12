package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.vo.MuseumInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MuseumInfoMapper extends BaseMapper<MuseumInfo> {
    Integer updateTodayMuseumInfo();

    MuseumInfoVO selectMuseumInfoDetailByMuseumId(@Param("museumId") Integer museumId);
}
