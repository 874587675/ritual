package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.MissMail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2025-02-18
 */
public interface MissMailService extends IService<MissMail> {

    String insertMissMail(MissMail missMail);

    String replyMissMail(MissMail missMail);

    IPage<MissMail> selectMissMailPage(Integer pageNo, Integer pageSize, Integer missId);
}
