package com.ruoyi.project.business.pay.wechat.vo;

import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.domain.ProduceOrder;
import com.ruoyi.project.business.vo.GiftItemVO;
import com.ruoyi.project.business.vo.ProduceOrderVO;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import lombok.Data;

@Data
public class WeChatJsapiPayVO extends PrepayRequest {
    /**
     * 礼物记录信息对象
     */
    private GiftItemVO giftItemVO;

    /**
     * 商品信息对象
     */
    private ProduceOrderVO produceOrderVO;
}

