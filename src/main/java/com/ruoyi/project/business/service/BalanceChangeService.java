package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.project.business.domain.BalanceChange;
import com.baomidou.mybatisplus.extension.service.IService;
public interface BalanceChangeService extends IService<BalanceChange>{

    IPage<BalanceChange> selectAllBalanceChanges(Integer pageNo, Integer pageSize, Integer walletId, String queryDate);
}
