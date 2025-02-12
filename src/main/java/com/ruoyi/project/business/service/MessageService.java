package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.MessageVO;

public interface MessageService extends IService<Message>{
    
    String addMessage(MessageVO messageVO);

    IPage<Message> getTopMessages(Integer pageNo, Integer pageSize, Integer roomId);

    IPage<MessageVO> getSubMessages(Integer pageNo, Integer pageSize, Integer parentId);

    String deleteMessage(Integer id);
}
