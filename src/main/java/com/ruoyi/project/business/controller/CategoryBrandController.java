package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.CategoryBrand;
import com.ruoyi.project.business.service.CategoryBrandService;
import com.ruoyi.project.business.service.impl.CategoryBrandServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/categoryBrand")
public class CategoryBrandController {

@Autowired
private CategoryBrandService categoryBrandService;


}
