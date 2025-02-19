package com.ruoyi.project.business.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.Item;

public interface ItemService extends IService<Item>{
    IPage<Item> getAllItemsForSimple(Integer pageNo, Integer pageSize, Integer typeId);
    
}
