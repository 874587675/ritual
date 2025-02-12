package com.ruoyi.project.business.controller;


import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Item;
import com.ruoyi.project.business.service.ItemService;
import com.ruoyi.project.business.vo.ItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "虚拟物品模块管理")
@RequestMapping("/item")
public class ItemController {
    @Resource
    private ItemService itemService;

    @ApiOperation("查询所有虚拟物品的信息")
    @GetMapping("/getAllItemsForSimple")
    public R<List<Item>> getAllItemsForSimple(@RequestParam(required = false) Integer typeId){
        return R.ok(itemService.getAllItemsForSimple(typeId));
    }
    
    
}
