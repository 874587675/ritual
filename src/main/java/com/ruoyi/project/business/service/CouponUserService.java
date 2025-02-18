package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.CouponUser;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CouponUserService extends IService<CouponUser>{


    IPage<CouponUser> selectCouponUser(Integer pageNo, Integer pageSize, String userId,Integer status);
}
