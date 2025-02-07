package com.ruoyi.project.business.controller;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Category;
import com.ruoyi.project.business.service.CategoryService;
import com.ruoyi.project.business.service.impl.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags = "商品分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @ApiOperation("获取商品分类列表(不分页)")
//    @GetMapping("/categoryList")
//    public R<List<Category>> categoryList(){
//        
//    }

}
