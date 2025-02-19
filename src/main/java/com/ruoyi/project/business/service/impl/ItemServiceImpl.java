package com.ruoyi.project.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.Item;
import com.ruoyi.project.business.mapper.ItemMapper;
import com.ruoyi.project.business.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService{

    @Resource
    private ItemMapper itemMapper;
    
    @Override
    public IPage<Item> getAllItemsForSimple(Integer pageNo, Integer pageSize, Integer typeId) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<Item>().eq(Item::getTypeId,typeId).orderByDesc(Item::getPrice));
    }
    
}
