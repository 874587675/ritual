package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/order")
@Api(tags = "管理端-订单管理模块")
public class AdminOrderController {

    @Resource
    private OrderService orderService;
    
    @ApiOperation("查询祭祀订单")
    @GetMapping("/selectOrder")
    public R<IPage<Order>> selectOrder(@RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(orderService.page(new Page<>(pageNo, pageSize)));
    }
}
