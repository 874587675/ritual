package com.ruoyi.project.business.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.MuseumSound;
import com.ruoyi.project.business.mapper.MuseumSoundMapper;
import com.ruoyi.project.business.service.MuseumSoundService;

import javax.annotation.Resource;

@Service
public class MuseumSoundServiceImpl extends ServiceImpl<MuseumSoundMapper, MuseumSound> implements MuseumSoundService{

    @Resource
    private MuseumSoundMapper museumSoundMapper;
    
    @Override
    public List<MuseumSound> selectAllMuseumSound() {
        return list();
    }
}
