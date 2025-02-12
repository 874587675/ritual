package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.MuseumSound;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MuseumSoundService extends IService<MuseumSound>{


    List<MuseumSound> selectAllMuseumSound();
}
