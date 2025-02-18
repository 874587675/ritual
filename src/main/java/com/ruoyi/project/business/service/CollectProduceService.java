package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.CollectProduce;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CollectProduceService extends IService<CollectProduce>{


    IPage<CollectProduce> collectProduceByUserId(Integer pageNo, Integer pageSize, String userId);

    String addOrCancelCollectProduce(String userId, Integer spuId);
}
