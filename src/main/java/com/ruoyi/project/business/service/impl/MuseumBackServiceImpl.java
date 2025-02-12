package com.ruoyi.project.business.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.MuseumBack;
import com.ruoyi.project.business.mapper.MuseumBackMapper;
import com.ruoyi.project.business.service.MuseumBackService;

import javax.annotation.Resource;

@Service
@Slf4j
public class MuseumBackServiceImpl extends ServiceImpl<MuseumBackMapper, MuseumBack> implements MuseumBackService{

    @Resource
    private MuseumBackMapper museumBackMapper;
    
    @Override
    public List<MuseumBack> selectAllMuseumBack() {
        return list();
    }
}
