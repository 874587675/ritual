package com.ruoyi.project.business.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.SkuInfoMapper;
import com.ruoyi.project.business.domain.SkuInfo;
import com.ruoyi.project.business.service.SkuInfoService;

import javax.annotation.Resource;

@Service
@Slf4j
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService{

    @Resource
    private SkuInfoMapper skuInfoMapper;

    
}
