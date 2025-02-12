package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.SpiritTitle;
import com.ruoyi.project.business.service.SpiritTitleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "祠堂馆灵牌主题背景管理")
@RequestMapping("/spiritTitle")
public class SpiritTitleController {

    @Resource
    private SpiritTitleService spiritTitleService;

    @ApiOperation("查询所有祠堂馆灵牌主题背景")
    @GetMapping("/selectAllSpiritTitle")
    public R<List<SpiritTitle>> selectAllSpiritTitle(){
        return R.ok(spiritTitleService.list());
    }
}
