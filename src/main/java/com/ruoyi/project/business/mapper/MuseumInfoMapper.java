package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.vo.MuseumInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MuseumInfoMapper extends BaseMapper<MuseumInfo> {
    Integer updateTodayMuseumInfo();

    MuseumInfoVO selectMuseumInfoDetailByMuseumId(@Param("museumId") Integer museumId);

    IPage<MuseumInfoVO> selectMuseumInfoFamilyByUserId(Page<MuseumInfo> museumInfoPage, @Param("userId") String userId);

    IPage<MuseumInfo> selectJoinFamilyMuseumInfoByUserId(Page<MuseumInfo> objectPage,@Param("userId") String userId);

    IPage<MuseumInfoVO> selectMuseumWatchByUserId(Page<MuseumInfo> objectPage,@Param("userId") String userId);
}
