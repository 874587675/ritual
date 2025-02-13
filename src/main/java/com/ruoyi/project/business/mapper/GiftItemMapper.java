package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.vo.GiftItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GiftItemMapper extends BaseMapper<GiftItem> {
    IPage<GiftItemVO> selectItemByUserId(Page<GiftItem> giftItemPage,@Param("userId") String userId);
}
