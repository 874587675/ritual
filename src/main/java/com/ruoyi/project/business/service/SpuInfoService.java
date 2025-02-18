package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.SpuInfoVO;

public interface SpuInfoService extends IService<SpuInfo>{


    IPage<SpuInfoVO> selectSpuInfoPage(Integer pageNo, Integer pageSize);

    IPage<SpuInfoVO> selectHotSpuInfoPage(Integer pageNo, Integer pageSize);

    SpuInfoVO selectSpuInfoById(Integer spuId);

    SpuInfoVO selectAllSkuInfo(Integer spuId);
}
