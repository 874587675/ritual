package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.ProduceOrder;
import com.ruoyi.project.business.mapper.ProduceOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.SpuComment;
import com.ruoyi.project.business.mapper.SpuCommentMapper;
import com.ruoyi.project.business.service.SpuCommentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentMapper, SpuComment> implements SpuCommentService {
    @Resource
    private SpuCommentMapper spuCommentMapper;
    @Resource
    private ProduceOrderMapper produceOrderMapper;

    @Override
    public IPage<SpuComment> selectSpuComment(Integer pageNo, Integer pageSize, Integer spuId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<SpuComment>().eq(SpuComment::getSpuId, spuId));
    }


    @Override
    @Transactional
    public String insertSpuComment(SpuComment spuComment) {
        try {
            if (spuComment == null) {
                log.error("用户评价参数不能为空");
                throw new ServiceException("用户评价参数不能为空");
            }
            int insertResult = spuCommentMapper.insert(spuComment);
            if (insertResult <= 0) {
                log.error("新增用户评价失败");
                throw new ServiceException("新增用户评价失败");
            }
            log.info("新增用户评价成功");
            // 修改订单状态
            ProduceOrder produceOrder = produceOrderMapper.selectById(spuComment.getOrderId());
            if (produceOrder == null) {
                log.error("该订单不存在,orderId为：{}", spuComment.getOrderId());
                throw new ServiceException("该订单不存在");
            }
            if (OrderStatus.WAIT_EVALUATE.getCode().equals(produceOrder.getOrderStatus())) {
                produceOrder.setOrderStatus(OrderStatus.COMPLETED.getCode());
                produceOrder.setOrderStatusDesc(OrderStatus.COMPLETED.getStatus());
                int updateResult = produceOrderMapper.updateById(produceOrder);
                if (updateResult <= 0) {
                    log.error("更新订单状态失败,orderId为：{}", produceOrder.getId());
                    throw new ServiceException("更新订单状态失败");
                }
                log.info("更新订单状态成功,orderId为：{}", produceOrder.getId());
                return "新增用户评价成功";
            }
            log.error("新增用户评价失败");
            return "新增用户评价失败";
        } catch (Exception e) {
            log.error("新增用户评价失败", e);
            throw new ServiceException("新增用户评价失败");
        }
    }
}
