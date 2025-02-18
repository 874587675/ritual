package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.ProduceOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ProduceOrderService extends IService<ProduceOrder>{


    Map<String, String> createProduceOrder(WeChatJsapiPayVO weChatJsapiPayVO);

    String payProduceCallback(HttpServletRequest request);

    String closeProduceOrder(Integer id);

    String confirmProduceOrder(Integer id);
}
