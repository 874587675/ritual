package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Category;
import com.ruoyi.project.business.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/category")
@Api(tags = "管理端-商品分类管理")
public class AdminCategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("创建商品分类")
    @PostMapping("/create")
    public R<Boolean> createCategory(@RequestBody Category category) {
        return R.ok(categoryService.save(category));
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/update")
    public R<Boolean> updateCategory(@RequestBody Category category) {
        return R.ok(categoryService.updateById(category));
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete")
    public R<Boolean> deleteCategory(@RequestParam Integer id) {
        return R.ok(categoryService.update(new LambdaUpdateWrapper<Category>().eq(Category::getId, id).set(Category::getIsDeleted, 1)));
    }

    @ApiOperation("查询商品分类")
    @GetMapping("/query")
    public R<IPage<Category>> queryCategory(@RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize) {
        return R.ok(categoryService.page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<Category>().eq(Category::getIsDeleted, 0).orderByDesc(Category::getUpdateTime)));
    }
}
