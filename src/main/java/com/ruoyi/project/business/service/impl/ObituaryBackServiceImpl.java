package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.ObituaryBack;
import com.ruoyi.project.business.mapper.ObituaryBackMapper;
import com.ruoyi.project.business.service.ObituaryBackService;

import javax.annotation.Resource;

@Service
@Slf4j
public class ObituaryBackServiceImpl extends ServiceImpl<ObituaryBackMapper, ObituaryBack> implements ObituaryBackService{
    @Resource
    private ObituaryBackMapper obituaryBackMapper;

    @Override
    public IPage<ObituaryBack> selectObituaryBackForImage(Integer pageNo, Integer pageSize) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<ObituaryBack>().eq(ObituaryBack::getType,1));
    }

    @Override
    public IPage<ObituaryBack> selectObituaryBackForContent(Integer pageNo, Integer pageSize) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<ObituaryBack>().eq(ObituaryBack::getType,2));
    }
}
