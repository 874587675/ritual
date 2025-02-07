package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.service.OrderService;
import com.ruoyi.project.business.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Api(tags = "订单管理模块")
public class OrderController {

    @Resource
    private OrderService orderService;
    
    @ApiOperation("新建订单信息")
    @PostMapping("/createOrder")
    public R<String> createOrder(@RequestBody OrderVO orderVO){
        return R.ok(orderService.createOrder(orderVO));
    }
}
