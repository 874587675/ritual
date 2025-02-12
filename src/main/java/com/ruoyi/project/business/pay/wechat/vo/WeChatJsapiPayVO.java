package com.ruoyi.project.business.pay.wechat.vo;

import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.vo.GiftItemVO;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import lombok.Data;

@Data
public class WeChatJsapiPayVO extends PrepayRequest {
    
    private GiftItemVO giftItemVO;
}

