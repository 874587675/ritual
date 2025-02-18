package com.ruoyi.project.business.controller;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.ProduceOrder;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.service.ProduceOrderService;
import com.ruoyi.project.business.service.impl.ProduceOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@Api(tags = "商城商品订单管理模块")
@RequestMapping("/produceOrder")
public class ProduceOrderController {

    @Resource
    private ProduceOrderService produceOrderService;

    @ApiOperation("创建商城商品订单信息（下单）")
    @PostMapping("/createProduceOrder")
    public R<Map<String, String>> createProduceOrder(@RequestBody WeChatJsapiPayVO WeChatJsapiPayVO){
        return R.ok(produceOrderService.createProduceOrder(WeChatJsapiPayVO));
    }

    @ApiOperation("商城商品订单支付回调")
    @PostMapping("/payProduceCallback")
    public R<String> payProduceCallback(@RequestBody HttpServletRequest request){
        return R.ok(produceOrderService.payProduceCallback(request));
    }
    
    @ApiOperation("用户关闭订单操作")
    @PostMapping("/closeProduceOrder")
    public R<String> closeProduceOrder(@RequestParam Integer id){
        return R.ok(produceOrderService.closeProduceOrder(id));
    }
    
    @ApiOperation("用户确认收货操作")
    @PostMapping("/confirmProduceOrder")
    public R<String> confirmProduceOrder(@RequestParam Integer id){
        return R.ok(produceOrderService.confirmProduceOrder(id));
    }

}
