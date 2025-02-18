package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.CollectProduceMapper;
import com.ruoyi.project.business.domain.CollectProduce;
import com.ruoyi.project.business.service.CollectProduceService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class CollectProduceServiceImpl extends ServiceImpl<CollectProduceMapper, CollectProduce> implements CollectProduceService {
    @Resource
    private CollectProduceMapper collectProduceMapper;

    @Override
    public IPage<CollectProduce> collectProduceByUserId(Integer pageNo, Integer pageSize, String userId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<CollectProduce>().eq(CollectProduce::getUserId, userId).eq(CollectProduce::getStatus, 1));
    }

    @Override
    @Transactional
    public String addOrCancelCollectProduce(String userId, Integer spuId) {
        try {
            CollectProduce collectProduce = getOne(new LambdaQueryWrapper<CollectProduce>().eq(CollectProduce::getUserId, userId).eq(CollectProduce::getSpuId, spuId));
            if (collectProduce == null) {
                //用户第一次收藏该物品
                collectProduce = new CollectProduce();
                collectProduce.setUserId(userId);
                collectProduce.setSpuId(spuId);
                collectProduce.setStatus(1);
                int insertResult = collectProduceMapper.insert(collectProduce);
                if (insertResult <= 0) {
                    log.error("用户{}，收藏商品{}，收藏失败", userId, spuId);
                    throw new ServiceException("收藏失败");
                }
                log.info("收藏商品成功");
                return "收藏商品成功";
            } else {
                //判断用户的收藏状态
                collectProduce.setStatus(collectProduce.getStatus() == 0 ? 1 : collectProduce.getStatus());
                int updateResult = collectProduceMapper.updateById(collectProduce);
                if (updateResult <= 0) {
                    log.error("用户{}，收藏商品{}，更新收藏商品状态失败", userId, spuId);
                    throw new ServiceException("更新收藏商品状态失败");
                }
                log.info("更新收藏商品状态成功");
                return "更新收藏商品状态成功";
            }
        } catch (Exception e) {
            log.error("更新收藏商品状态失败", e);
            throw new ServiceException("更新收藏商品状态失败");
        }
    }
}
