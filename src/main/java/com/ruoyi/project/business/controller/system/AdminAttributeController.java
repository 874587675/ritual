package com.ruoyi.project.business.controller.system;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Attribute;
import com.ruoyi.project.business.service.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "管理端-商品规格管理")
@RequestMapping("/system/attribute")
public class AdminAttributeController {

    @Resource
    private AttributeService attributeService;

    @ApiOperation("创建商品规格信息")
    @PostMapping("/create")
    public R<Boolean> create(@RequestBody Attribute attribute) {
        return R.ok(attributeService.save(attribute));
    }

    @ApiOperation("修改商品规格信息")
    @PostMapping("/update")
    public R<Boolean> update(@RequestBody Attribute attribute) {
        return R.ok(attributeService.updateById(attribute));
    }

    @ApiOperation("删除商品规格信息")
    @PostMapping("/delete")
    public R<Boolean> delete(@RequestParam Integer id) {
        return R.ok(attributeService.removeById(id));
    }
}
