package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.SpuInfo;
import com.ruoyi.project.business.mapper.SkuInfoMapper;
import com.ruoyi.project.business.mapper.SpuInfoMapper;
import com.ruoyi.project.business.service.SpuInfoService;
import com.ruoyi.project.business.vo.SpuInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService{

    @Resource
    private SpuInfoMapper spuInfoMapper;
    
    @Resource
    private SkuInfoMapper skuInfoMapper;
    
    @Override
    public IPage<SpuInfoVO> selectSpuInfoPage(Integer pageNo, Integer pageSize) {
        // 查出所有的Spu商品，然后统计每个商品下的Sku商品的最低价格和销量
        List<SpuInfoVO> spuInfoVOS = spuInfoMapper.selectAllSpuInfo();
        Page<SpuInfoVO> page = new Page<>(pageNo, pageSize);
        page.setRecords(spuInfoVOS);
        return page;
    }
}
