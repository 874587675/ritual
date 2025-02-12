package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.MuseumArticle;
import com.ruoyi.project.business.service.MuseumArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "馆藏追忆文章信息管理")
@RequestMapping("/museumArticle")
public class MuseumArticleController {

    @Resource
    private MuseumArticleService museumArticleService;

    @ApiOperation("查询所有该馆藏的追忆文章")
    @GetMapping("/selectAllArticleByMuseumId")
    public R<IPage<MuseumArticle>> selectAllArticleByMuseumId(@RequestParam(defaultValue = "1") Integer PageNo,
                                                              @RequestParam(defaultValue = "30") Integer pageSize,
                                                              @RequestParam Integer MuseumId) {
        return R.ok(museumArticleService.selectAllArticleByMuseumId(PageNo, pageSize, MuseumId));
    }
    
    @ApiOperation("添加该馆藏的追忆文章")
    @PostMapping("/addArticleByMuseumId")
    public R<String> addArticleByMuseumId(@RequestBody MuseumArticle museumArticle) {
        return R.ok(museumArticleService.addArticleByMuseumId(museumArticle));
    }
}
