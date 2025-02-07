package com.ruoyi.project.business.controller;
import com.ruoyi.project.business.domain.OrderSuccess;
import com.ruoyi.project.business.service.OrderSuccessService;
import com.ruoyi.project.business.service.impl.OrderSuccessServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/orderSuccess")
@Api(tags = "支付成功订单管理模块")
public class OrderSuccessController {

    @Autowired
    private OrderSuccessService orderSuccessService;



}
