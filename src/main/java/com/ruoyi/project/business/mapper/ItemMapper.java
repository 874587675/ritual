package com.ruoyi.project.business.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.business.domain.Item;
import com.ruoyi.project.business.vo.ItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {
    
}
