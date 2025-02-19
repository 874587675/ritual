package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.MuseumBack;

public interface MuseumBackService extends IService<MuseumBack>{
    IPage<MuseumBack> selectAllMuseumBack(Integer pageNo,Integer pageSize);
}
