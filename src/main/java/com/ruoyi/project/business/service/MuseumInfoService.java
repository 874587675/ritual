package com.ruoyi.project.business.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.MuseumInfoVO;

public interface MuseumInfoService extends IService<MuseumInfo>{
    
    IPage<MuseumInfo> selectMuseumInfoPage(Integer pageNo, Integer pageSize, Integer type);

    IPage<MuseumInfo> selectTodayMuseumInfo(Integer pageNo, Integer pageSize);

    IPage<MuseumInfo> selectNewMuseumInfo(Integer pageNo, Integer pageSize);

    String createMuseumInfoSelfByUserId(MuseumInfoVO museumInfoVO);

    String createMuseumInfoFamilyByUserId(MuseumInfoVO museumInfoVO);

    IPage<MuseumInfo> selectMuseumInfoSelfByUserId(Integer pageNo, Integer pageSize, Integer userId);
    
    String updatePictureByMuseumId(Integer museumId, String picture);

    String updateVideoByMuseumId(Integer museumId, String video);

    MuseumInfoVO selectMuseumInfoDetailByMuseumId(Integer museumId);

    String updateMuseumStatusByMuseumId(Integer museumId);
}
