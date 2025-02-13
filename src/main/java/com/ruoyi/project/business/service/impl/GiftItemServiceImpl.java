package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.framework.redis.ZSetComponent;
import com.ruoyi.project.business.domain.MuseumInfo;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.MuseumInfoMapper;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.vo.GiftItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.GiftItem;
import com.ruoyi.project.business.mapper.GiftItemMapper;
import com.ruoyi.project.business.service.GiftItemService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class GiftItemServiceImpl extends ServiceImpl<GiftItemMapper, GiftItem> implements GiftItemService{

    @Resource
    private GiftItemMapper giftItemMapper;
    
    @Resource
    private ZSetComponent zSetComponent;
    
    @Resource
    private MuseumInfoMapper museumInfoMapper;

    @Override
    public IPage<GiftItem> selectGiftItemByMuseumId(Integer pageNo, Integer pageSize, Integer museumId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<GiftItem>()
                .eq(GiftItem::getMuseumId, museumId)
                .eq(GiftItem::getOrderStatus, OrderStatus.PAYED)
                .orderByDesc(GiftItem::getCreateTime));
    }

    @Override
    public List<GiftItemVO> selectGiftItemRankByMuseumId(Integer museumId) {
        return zSetComponent.getTopNByRoomId(10, museumId);
    }

    @Override
    public List<GiftItemVO> selectGiftItemRank() {
        return zSetComponent.getTopN(10);
    }

    @Override
    public Long selectLightedCandlesCountByMuseumId(Integer museumId) {
        MuseumInfo museumInfo = museumInfoMapper.selectById(museumId);
        if (museumInfo == null){
            log.error("该纪念馆不存在");
            throw new ServiceException("该纪念馆不存在");
        }
        return giftItemMapper.selectCount(new LambdaQueryWrapper<GiftItem>().eq(GiftItem::getItemId,1).eq(GiftItem::getMuseumId,museumId));
    }

    @Override
    public IPage<GiftItemVO> selectItemByUserId(Integer pageNo, Integer pageSize, String userId) {
        return giftItemMapper.selectItemByUserId(new Page<>(pageNo,pageSize),userId);
    }
}
