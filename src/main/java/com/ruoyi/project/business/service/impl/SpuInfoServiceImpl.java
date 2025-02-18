package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.SkuInfo;
import com.ruoyi.project.business.domain.SkuSaleAttributeValue;
import com.ruoyi.project.business.domain.SpuInfo;
import com.ruoyi.project.business.mapper.SkuInfoMapper;
import com.ruoyi.project.business.mapper.SpuInfoMapper;
import com.ruoyi.project.business.service.SpuInfoService;
import com.ruoyi.project.business.vo.SkuInfoVO;
import com.ruoyi.project.business.vo.SpuInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {

    @Resource
    private SpuInfoMapper spuInfoMapper;

    @Resource
    private SkuInfoMapper skuInfoMapper;

    @Override
    public IPage<SpuInfoVO> selectSpuInfoPage(Integer pageNo, Integer pageSize) {
        // 查出所有的Spu商品，然后统计每个商品下的Sku商品的最低价格和销量
        return spuInfoMapper.selectAllSpuInfo(new Page<>(pageNo, pageSize));
    }

    @Override
    public IPage<SpuInfoVO> selectHotSpuInfoPage(Integer pageNo, Integer pageSize) {
        // 查出所有的Spu商品，然后统计每个商品下的Sku商品的最低价格和销量,按销量倒序排序
        return spuInfoMapper.selectHotSpuInfoPage(new Page<>(pageNo, pageSize));
    }

    @Override
    public SpuInfoVO selectSpuInfoById(Integer spuId) {
        //查询单个spu商品具体的详情
        return spuInfoMapper.selectSpuInfoById(spuId);
    }

    @Override
    public SpuInfoVO selectAllSkuInfo(Integer spuId) {
        //查询出当前spuId商品的信息
        SpuInfo spuInfo = spuInfoMapper.selectOne(new LambdaQueryWrapper<SpuInfo>().eq(SpuInfo::getId, spuId));
        //查询当前商品spuId下所有的销售商品sku
        List<SkuInfo> skuInfos = skuInfoMapper.selectList(new LambdaQueryWrapper<SkuInfo>().eq(SkuInfo::getSpuId, spuId));
        //将SkuInfo集合转换成SkuInfoVO集合
        List<SkuInfoVO> skuInfosVO = skuInfos.stream().map(skuInfo -> {
            SkuInfoVO skuInfoVO = new SkuInfoVO();
            BeanUtils.copyProperties(skuInfo, skuInfoVO);
            return skuInfoVO;
        }).collect(Collectors.toList());
        
        //查询出所有sku销售商品对应的销售属性
        List<SkuSaleAttributeValue> attrValueList = skuInfoMapper.selectAttrValueList(spuId);
        Map<Integer, List<SkuSaleAttributeValue>> collects = attrValueList.stream().collect(Collectors.groupingBy(SkuSaleAttributeValue::getSkuId));
        skuInfosVO.forEach(skuInfo -> {
            //获取该skuId的销售属性
            List<SkuSaleAttributeValue> attrValues = collects.get(skuInfo.getId());
            //将对应的销售属性存入对应的销售商品对象中
            skuInfo.setSkuSaleAttributeValueList(attrValues);
        });
        List<Map<String, Object>> attributesMaps = convertAttributesToFrontendFormat(attrValueList);
        SpuInfoVO spuInfoVO = new SpuInfoVO();
        BeanUtils.copyProperties(spuInfo, spuInfoVO);
        spuInfoVO.setAttributeList(attributesMaps);
        spuInfoVO.setSkuInfoVOList(skuInfosVO);
        return spuInfoVO;
    }

    /**
     * 根据 spuId 查询并转换规格数据为前端所需格式
     */
    public List<Map<String, Object>> convertAttributesToFrontendFormat(List<SkuSaleAttributeValue> attrValueList) {
        // 1. 按属性ID进行分组
        Map<String, Set<String>> attributeValuesMap = attrValueList.stream()
                .collect(Collectors.groupingBy(
                        SkuSaleAttributeValue::getAttributeName,
                        Collectors.mapping(SkuSaleAttributeValue::getAttributeValue, Collectors.toSet())
                ));
        // 2. 将分组后的数据转为前端所需格式
        List<Map<String, Object>> attributesList = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : attributeValuesMap.entrySet()) {
            Map<String, Object> attribute = new HashMap<>();
            attribute.put("name", entry.getKey());  // 属性名称
            attribute.put("values", entry.getValue());  // 属性值列表
            attributesList.add(attribute);
        }
        
        return attributesList;
    }
}
