package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.MuseumBack;
import com.ruoyi.project.business.mapper.MuseumBackMapper;
import com.ruoyi.project.business.service.MuseumBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MuseumBackServiceImpl extends ServiceImpl<MuseumBackMapper, MuseumBack> implements MuseumBackService{

    @Resource
    private MuseumBackMapper museumBackMapper;
    
    @Override
    public IPage<MuseumBack> selectAllMuseumBack(Integer pageNo,Integer pageSize) {
        return page(new Page<>(pageNo,pageSize));
    }
}
