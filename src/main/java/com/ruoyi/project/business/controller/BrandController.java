package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.Brand;
import com.ruoyi.project.business.service.BrandService;
import com.ruoyi.project.business.service.impl.BrandServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 品牌表，一个品牌下有多个商品（spu），一对多关系(t_brand)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/brand")
public class BrandController {

@Autowired
private BrandService brandService;


}
