package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.SpuInfoVO;

public interface SpuInfoService extends IService<SpuInfo>{


    IPage<SpuInfoVO> selectSpuInfoPage(Integer pageNo, Integer pageSize);
}
