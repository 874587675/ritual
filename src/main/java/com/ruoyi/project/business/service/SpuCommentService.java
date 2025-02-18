package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.SpuComment;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SpuCommentService extends IService<SpuComment>{
    
    IPage<SpuComment> selectSpuComment(Integer pageNo, Integer pageSize, Integer spuId);

    String insertSpuComment(SpuComment spuComment);
}
