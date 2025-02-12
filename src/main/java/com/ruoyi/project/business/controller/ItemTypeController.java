package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.ItemType;
import com.ruoyi.project.business.service.ItemTypeService;
import com.ruoyi.project.business.service.impl.ItemTypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/itemType")
@Api(tags = "虚拟物品类型模块管理")
public class ItemTypeController {

    @Resource
    private ItemTypeService itemTypeService;

    @ApiOperation("查询所有虚拟物品类型")
    @GetMapping("selectAllItemTypes")
    public R<List<ItemType>> selectAllItemTypes(){
        return R.ok(itemTypeService.list());
    }
}
