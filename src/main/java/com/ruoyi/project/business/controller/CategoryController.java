package com.ruoyi.project.business.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Category;
import com.ruoyi.project.business.service.CategoryService;
import com.ruoyi.project.business.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "商品分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("分页获取商品分类列表")
    @GetMapping("/selectCategory")
    public R<IPage<Category>> selectCategory(@RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(categoryService.page(new Page<>(pageNo,pageSize)));
    }

}
