package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.LikeMessage;
import com.ruoyi.project.business.mapper.LikeMessageMapper;
import com.ruoyi.project.business.service.LikeMessageService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class LikeMessageServiceImpl extends ServiceImpl<LikeMessageMapper, LikeMessage> implements LikeMessageService {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private LikeMessageMapper likeMessageMapper;

    @Override
    @Transactional
    public String likeOrCancelLikeMessage(Integer messageId) {
        try {
            Message message = messageMapper.selectById(messageId);
            if (message == null) {
                log.error("该消息不存在");
                throw new ServiceException("该消息不存在");
            }
            LikeMessage likeMessage = likeMessageMapper.selectOne(new LambdaQueryWrapper<LikeMessage>().eq(LikeMessage::getMessageId, messageId));
            if (likeMessage == null) {
                likeMessage = new LikeMessage();
                likeMessage.setLikeStatus(1);
                int insertResult = likeMessageMapper.insert(likeMessage);
                if (insertResult <= 0) {
                    log.error("点赞失败，消息ID: {}", messageId);
                    throw new ServiceException("点赞成功");
                }
                log.info("点赞成功，消息ID: {}", messageId);
                return "点赞成功";
            }
            likeMessage.setLikeStatus(likeMessage.getLikeStatus() == 0 ? 1 : likeMessage.getLikeStatus());
            int updateResult = likeMessageMapper.updateById(likeMessage);
            if (updateResult <= 0) {
                log.error("点赞状态更新失败，消息ID: {}", messageId);
                throw new ServiceException("点赞状态更新失败");
            }
            log.info("点赞状态更新成功，消息ID: {}", messageId);
            return "点赞状态更新成功";
        } catch (Exception e) {
            log.error("点赞状态更新失败,原因: {}", e.getMessage());
            throw new ServiceException("点赞状态更新失败");
        }
    }
}
