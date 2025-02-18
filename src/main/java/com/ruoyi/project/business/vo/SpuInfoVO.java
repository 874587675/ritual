package com.ruoyi.project.business.vo;



import com.ruoyi.project.business.domain.SpuInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
     * 原价价格
     */
    private BigDecimal originPrice;
    
    /**
     * 销量
     */
    private Integer saleCount;

    /**
     * 默认图片
     */
    private String imgUrl;

    /**
     * sku销售商品List
     */
    List<SkuInfoVO> skuInfoVOList;

    /**
     * 销售商品规格属性
     */
    List<Map<String, Object>> attributeList;
}
