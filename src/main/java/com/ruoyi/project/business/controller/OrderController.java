package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.service.OrderService;
import com.ruoyi.project.business.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Api(tags = "订单管理模块")
public class OrderController {

    @Resource
    private OrderService orderService;
    
    @ApiOperation("新建虚拟物品订单信息(下单)")
    @PostMapping("/createItemOrder")
    public R<Map<String, String>> createItemOrder(@RequestBody WeChatJsapiPayVO WeChatJsapiPayVO){
        return R.ok(orderService.createItemOrder(WeChatJsapiPayVO));
    }
    
    @ApiOperation("虚拟物品订单支付回调")
    @PostMapping("/payItemCallback")
    public R<String> payItemCallback(@RequestBody HttpServletRequest request){
        return R.ok(orderService.payItemCallback(request));
    }
    
    
}
