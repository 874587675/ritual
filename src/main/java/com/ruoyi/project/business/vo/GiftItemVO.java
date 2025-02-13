package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:GiftItemVO
 * @description:
 * @author: zgc
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftItemVO extends GiftItem {
    /**
     * 有效期天数
     */
    public Integer validateDays;

    /**
     * 排行名称
     */
    public Integer rank;
    
    /**
     * 用户昵称
     */
    public String nickName;

    /**
     * 虚拟物品对象
     */
    public Item item;

    /**
     * 纪念馆名称
     */
    public String museumName;

    public GiftItemVO(String userId,String nickName, Double score, Integer rank) {
        this.userId = userId;
        this.nickName = nickName;
        this.score = score;
        this.rank = rank;
    }
    
}
