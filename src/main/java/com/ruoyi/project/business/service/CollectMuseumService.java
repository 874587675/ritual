package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.CollectMuseum;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CollectMuseumService extends IService<CollectMuseum>{


    IPage<CollectMuseum> selectCollectMuseumPageByUserId(Integer pageNo, Integer pageSize, String userId);

    Boolean addOrCancelCollectMuseum(String userId, Integer museumId);
}
