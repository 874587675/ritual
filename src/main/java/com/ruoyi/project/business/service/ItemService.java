package com.ruoyi.project.business.service;


import com.ruoyi.project.business.domain.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.ItemVO;

import java.util.List;

public interface ItemService extends IService<Item>{
    List<Item> getAllItemsForSimple(Integer typeId);
    
}
