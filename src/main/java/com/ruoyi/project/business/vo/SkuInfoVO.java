package com.ruoyi.project.business.vo;

import com.ruoyi.project.business.domain.SkuInfo;
import com.ruoyi.project.business.domain.SkuSaleAttributeValue;
import com.ruoyi.project.business.domain.SpuInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName:SkuInfoVO
 * @description:
 * @author: zgc
 **/
@Data
public class SkuInfoVO extends SkuInfo {
    /**
     * 销售属性列表
     */
    private List<SkuSaleAttributeValue> skuSaleAttributeValueList;
}
