package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.ObituaryBack;
import com.baomidou.mybatisplus.extension.service.IService;
public interface ObituaryBackService extends IService<ObituaryBack>{


    IPage<ObituaryBack> selectObituaryBackForImage(Integer pageNo, Integer pageSize);

    IPage<ObituaryBack> selectObituaryBackForContent(Integer pageNo, Integer pageSize);
}
