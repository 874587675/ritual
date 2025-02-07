package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.Order;
import com.ruoyi.project.business.vo.OrderVO;

public interface OrderService extends IService<Order>{
    String createOrder(OrderVO orderVO);
}
