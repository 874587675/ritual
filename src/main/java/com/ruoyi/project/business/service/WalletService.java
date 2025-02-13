package com.ruoyi.project.business.service;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Wallet;
import com.baomidou.mybatisplus.extension.service.IService;
public interface WalletService extends IService<Wallet>{
    Wallet selectWalletByUserId(String userId);
}
