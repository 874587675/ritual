package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.business.domain.SpuInfo;
import com.ruoyi.project.business.vo.SpuInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {

    List<SpuInfoVO> selectAllSpuInfo();
    
}
