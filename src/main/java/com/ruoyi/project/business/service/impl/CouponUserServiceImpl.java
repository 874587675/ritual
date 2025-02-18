package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.CouponUser;
import com.ruoyi.project.business.mapper.CouponUserMapper;
import com.ruoyi.project.business.service.CouponUserService;

import javax.annotation.Resource;

@Service
@Slf4j
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements CouponUserService{
    @Resource
    private CouponUserMapper couponUserMapper;

    @Override
    public IPage<CouponUser> selectCouponUser(Integer pageNo, Integer pageSize, String userId,Integer status) {
        return page(new Page<>(pageNo,pageSize),new LambdaQueryWrapper<CouponUser>()
                .eq(CouponUser::getUserId,userId)
                .eq(CouponUser::getIsDeleted,0)
                .eq(status!=null, CouponUser::getStatus,status)
                .orderByAsc(CouponUser::getStatus)
                .orderByDesc(CouponUser::getExpireTime)
        );
    }
}
