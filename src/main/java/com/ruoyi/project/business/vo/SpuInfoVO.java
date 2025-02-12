package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.SpuInfo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName:SpuInfoVO
 * @description:
 * @author: zgc
 **/
@Data
public class SpuInfoVO extends SpuInfo {
    
    /**
     * 最低售价
     */
    private BigDecimal price;
    
    /**
     * 销量
     */
    private Integer saleCount;

    /**
     * 默认图片
     */
    private String imgUrl;
}
