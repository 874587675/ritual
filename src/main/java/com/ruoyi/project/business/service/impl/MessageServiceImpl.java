package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.domain.User;
import com.ruoyi.project.business.mapper.MessageMapper;
import com.ruoyi.project.business.mapper.UserMapper;
import com.ruoyi.project.business.service.MessageService;
import com.ruoyi.project.business.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    @RateLimiter(key = "addMessage", time = 10, count = 3)
    public String addMessage(MessageVO messageVO) {
        try {
            //设置顶级ID
            messageVO.setTopId(messageVO.getTopId() == null ? 0 : messageVO.getTopId());
            //设置父id
            messageVO.setParentId(messageVO.getParentId() == null ? 0 : messageVO.getParentId());
            //查询发送者的信息
            if (messageVO.getUserId() == null) {
                log.error("发送者用户ID不能为空");
                throw new ServiceException("发送者用户ID不能为空");
            }
            User sender = userMapper.selectById(messageVO.getUserId());
            if (sender == null) {
                log.error("发送者用户信息不存在");
                throw new ServiceException("发送者用户信息不存在");
            }else {
                messageVO.setNickName(sender.getUsername());
            }
            if (messageVO.getParentId() != 0){
                //查询回复者的信息
                User replier = userMapper.selectById(messageVO.getReplyUserId());
                if (replier == null) {
                    log.error("回复者用户信息不存在");
                    throw new ServiceException("回复者用户信息不存在");
                }else {
                    messageVO.setReplyName(replier.getUsername());
                }
            }
            messageMapper.insert(messageVO);
            log.info("消息添加成功");
            return "消息添加成功";
        } catch (Exception e) {
            log.error("消息添加失败", e);
            throw new ServiceException("消息添加失败");
        }
    }

    @Override
    public IPage<Message> getTopMessages(Integer pageNo, Integer pageSize, Integer roomId) {
        return page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<Message>()
                .eq(Message::getRoomId, roomId)
                .eq(Message::getParentId, 0)
                .eq(Message::getIsDeleted, 0)
        );
    }

    @Override
    public IPage<MessageVO> getSubMessages(Integer pageNo, Integer pageSize, Integer topId) {
        //分页查询出顶级ID下所有的子留言
        return messageMapper.getSubMessages(new Page<>(pageNo, pageSize), topId);
    }

    @Override
    public String deleteMessage(Integer id) {
        //逻辑删除消息
        try {
            Message message = messageMapper.selectById(id);
            if (message == null) {
                log.error("消息不存在");
                throw new ServiceException("消息不存在");
            }
            message.setIsDeleted(1);
            messageMapper.updateById(message);
            log.info("消息删除成功");
            return "消息删除成功";
        } catch (Exception e) {
            log.error("消息删除失败", e);
            throw new ServiceException("消息删除失败");
        }
    }
}
