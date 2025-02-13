package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.GiftItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.GiftItemVO;

import java.util.List;

public interface GiftItemService extends IService<GiftItem>{

    IPage<GiftItem> selectGiftItemByMuseumId(Integer pageNo, Integer pageSize, Integer roomId);

    List<GiftItemVO> selectGiftItemRankByMuseumId(Integer roomId);

    List<GiftItemVO> selectGiftItemRank();

    Long selectLightedCandlesCountByMuseumId(Integer museumId);
    
    IPage<GiftItemVO> selectItemByUserId(Integer pageNo, Integer pageSize, String userId);
}
