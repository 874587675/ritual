package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.MuseumInfo;
import lombok.Data;

/**
 * @ClassName:MuseumInfoVO
 * @description:
 * @author: zgc
 **/
@Data
public class MuseumInfoVO extends MuseumInfo {
    /**
     * 手机验证码
     */
    private String code;
    
    /**
     * 距离忌日天数
     */
    private Integer deathDays;
    
    /**
     * 距离诞辰天数
     */
    private Integer birthDays;

//    /**
//     * 离开年数
//     */
//    private Integer leaveYears;
//
//    /**
//     * 离开月数
//     */
//    private Integer leaveMonths;
    
    /**
     * 亲友团祭拜次数
     */
    private Integer familyWorshipCount;
    
    /**
     * 点亮蜡烛次数
     */
    private Integer lightCandleCount;

    /**
     * 点亮健康平安灯的数量
     */
    private Integer lightHealthCount;

    /**
     * 点亮功成名就灯的数量
     */
    private Integer lightAchievementCount;

    /**
     * 点亮招财进宝灯的数量
     */
    private Integer lightTreasureCount;

    /**
     * 点亮荣华富贵灯的数量
     */
    private Integer lightWealthCount;

    /**
     * 供奉灵位的数量
     */
    private Integer spiritPositionCount;

    /**
     * 关注纪念馆的状态
     */
    private Integer watchStatus;
}
