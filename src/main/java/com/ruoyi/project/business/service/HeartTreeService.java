package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.HeartTree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.vo.HeartTreeVO;
import com.ruoyi.project.business.vo.MessageVO;

public interface HeartTreeService extends IService<HeartTree>{
    IPage<HeartTree> selectAllHeartTree(Integer pageNo, Integer pageSize);

    String addHeartTree(HeartTreeVO heartTreeVO);

    HeartTree selectHeartTreeDetails(Integer treeId);

    IPage<Message> selectTopMessages(Integer pageNo, Integer pageSize, Integer treeId);

    IPage<MessageVO> getSubMessages(Integer pageNo, Integer pageSize, Integer topId);

    String deleteHeartTree(Integer treeId);
}
