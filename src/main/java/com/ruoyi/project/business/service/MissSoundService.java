package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.MissSound;
import com.ruoyi.project.business.vo.MissSoundVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2025-02-18
 */
public interface MissSoundService extends IService<MissSound> {

    String createStar(MissSoundVO missSoundVO);

    IPage<MissSound> myStars(Integer pageNo, Integer pageSize, Integer userId);
}
