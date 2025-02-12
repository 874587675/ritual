package com.ruoyi.project.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.project.business.domain.Message;
import com.ruoyi.project.business.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    Page<MessageVO> getSubMessages(IPage<MessageVO> page, @Param("topId") Integer topId);
}