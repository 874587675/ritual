package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface OrderService extends IService<Order>{
    Map<String, String> createItemOrder(WeChatJsapiPayVO WeChatJsapiPayVO);

    String payItemCallback(HttpServletRequest request);
}
