package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.domain.ObituaryNotice;
import lombok.Data;

import java.util.List;

@Data
public class ObituaryNoticeVO extends ObituaryNotice {
    /**
     * 手机验证码
     */
    private String code;

    /**
     * 纪念馆名称
     */
    private String museumName;

    /**
     * 追思灯数量
     */
    private Integer lightMemorialCount;

    /**
     * 平安灯数量
     */
    private Integer lightSafeCount;

    /**
     * 祈福灯数量
     */
    private Integer lightPrayCount;

    /**
     * 孝亲灯数量
     */
    private Integer lightFilialCount;

    /**
     * 礼物记录信息对象
     */
    private List<GiftItem> giftItemList;
}