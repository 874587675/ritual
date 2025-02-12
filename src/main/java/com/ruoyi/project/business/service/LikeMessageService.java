package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.LikeMessage;
import com.baomidou.mybatisplus.extension.service.IService;
public interface LikeMessageService extends IService<LikeMessage>{
    String likeOrCancelLikeMessage(Integer messageId);
}
