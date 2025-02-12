package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.MuseumBack;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MuseumBackService extends IService<MuseumBack>{


    List<MuseumBack> selectAllMuseumBack();
}
