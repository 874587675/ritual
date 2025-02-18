package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.SpuInfo;
import com.ruoyi.project.business.vo.SpuInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {

    IPage<SpuInfoVO> selectAllSpuInfo(Page<SpuInfo> page);

    IPage<SpuInfoVO> selectHotSpuInfoPage(Page<SpuInfo> page);

    SpuInfoVO selectSpuInfoById(@Param("spuId") Integer spuId);
}
