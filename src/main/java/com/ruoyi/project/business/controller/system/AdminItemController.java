package com.ruoyi.project.business.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Item;
import com.ruoyi.project.business.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-虚拟物品模块管理")
@RequestMapping("/system/item")
public class AdminItemController {
    @Resource
    private ItemService itemService;

    @ApiOperation("查询所有虚拟物品的信息")
    @GetMapping("/getAllItemsForSimple")
    public R<IPage<Item>> getAllItemsForSimple(@RequestParam(defaultValue = "1") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(required = false) Integer typeId) {
        return R.ok(itemService.getAllItemsForSimple(pageNo, pageSize, typeId));
    }

    @ApiOperation("新增虚拟物品信息")
    @PostMapping("/addItem")
    public R<Boolean> addItem(@RequestBody Item item) {
        return R.ok(itemService.save(item));
    }

    @ApiOperation("修改虚拟物品信息")
    @PostMapping("/updateItem")
    public R<Boolean> updateItem(@RequestBody Item item) {
        return R.ok(itemService.updateById(item));
    }

    @ApiOperation("删除虚拟物品信息")
    @PostMapping("/deleteItem")
    public R<Boolean> deleteItem(@RequestParam Long id) {
        return R.ok(itemService.removeById(id));
    }
}
