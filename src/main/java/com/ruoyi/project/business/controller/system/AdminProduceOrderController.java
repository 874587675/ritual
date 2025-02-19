package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.ProduceOrder;
import com.ruoyi.project.business.service.ProduceOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-商城商品订单管理模块")
@RequestMapping("/system/produceOrder")
public class AdminProduceOrderController {

    @Resource
    private ProduceOrderService produceOrderService;

    @ApiOperation("查询商品订单信息")
    @GetMapping("/selectProduceOrder")
    public R<IPage<ProduceOrder>> selectProduceOrder(@RequestParam(defaultValue = "1") Integer pageNo,
                                                     @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(produceOrderService.page(new Page<>(pageNo, pageSize)));
    }


}
