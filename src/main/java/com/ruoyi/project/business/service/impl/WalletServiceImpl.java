package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.framework.web.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.Wallet;
import com.ruoyi.project.business.mapper.WalletMapper;
import com.ruoyi.project.business.service.WalletService;

import javax.annotation.Resource;

@Service
@Slf4j
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService{
    @Resource
    private WalletMapper walletMapper;

    @Override
    public Wallet selectWalletByUserId(String userId) {
        return walletMapper.selectOne(new LambdaQueryWrapper<Wallet>().eq(Wallet::getUserId,userId));
    }
}
