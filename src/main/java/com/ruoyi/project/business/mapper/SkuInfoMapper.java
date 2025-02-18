package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.business.domain.SkuInfo;
import com.ruoyi.project.business.domain.SkuSaleAttributeValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
    List<SkuSaleAttributeValue> selectAttrValueList(@Param("spuId") Integer spuId);
}
