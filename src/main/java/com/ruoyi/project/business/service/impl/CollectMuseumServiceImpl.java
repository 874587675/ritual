package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.CollectMuseum;
import com.ruoyi.project.business.mapper.CollectMuseumMapper;
import com.ruoyi.project.business.service.CollectMuseumService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectMuseumServiceImpl extends ServiceImpl<CollectMuseumMapper, CollectMuseum> implements CollectMuseumService{
    @Override
    public IPage<CollectMuseum> selectCollectMuseumPageByUserId(Integer pageNo, Integer pageSize, String userId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<CollectMuseum>().eq(CollectMuseum::getUserId, userId).orderByDesc(CollectMuseum::getUpdateTime));
    }

    @Override
    @Transactional
    public Boolean addOrCancelCollectMuseum(String userId, Integer museumId) {
        CollectMuseum collectMuseum = getOne(new LambdaQueryWrapper<CollectMuseum>().eq(CollectMuseum::getUserId, userId).eq(CollectMuseum::getMuseumId, museumId));
        if (collectMuseum == null) {
            //用户第一次收藏该馆藏信息
            CollectMuseum cmBuilder = CollectMuseum.builder().userId(userId).museumId(museumId).status(1).build();
            return save(cmBuilder);
        } else {
            collectMuseum.setStatus(collectMuseum.getStatus() == 1? 0 : 1);
            return updateById(collectMuseum);
        }
    }
}
