package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.mapper.BalanceChangeMapper;
import com.ruoyi.project.business.domain.BalanceChange;
import com.ruoyi.project.business.service.BalanceChangeService;

import javax.annotation.Resource;

@Service
@Slf4j
public class BalanceChangeServiceImpl extends ServiceImpl<BalanceChangeMapper, BalanceChange> implements BalanceChangeService {

    @Resource
    private BalanceChangeMapper balanceChangeMapper;

    @Override
    public IPage<BalanceChange> selectAllBalanceChanges(Integer pageNo, Integer pageSize, Integer walletId, String queryDate) {
        String startDateStr = null;
        String endDateStr = null;
        // 解析年月
        if (queryDate != null && !queryDate.isEmpty()) {
            String[] parts = queryDate.split("-");
            int year = Integer.parseInt(parts[0]);
            int monthInt = Integer.parseInt(parts[1]);
            // 计算该月的第一天
            LocalDate startDate = LocalDate.of(year, monthInt, 1);
            // 计算该月的最后一天
            LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
            // 格式化为 DATETIME 格式（yyyy-MM-dd HH:mm:ss）
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            startDateStr = startDate.atStartOfDay().format(formatter);  // '2024-10-01 00:00:00'
            endDateStr = endDate.atTime(23, 59, 59).format(formatter);
        }
        return page(new Page<>(pageNo, pageSize),
                new LambdaQueryWrapper<BalanceChange>()
                        .eq(BalanceChange::getWalletId, walletId)
                        .between(queryDate!=null && !queryDate.isEmpty(),BalanceChange::getUpdateTime,startDateStr,endDateStr)
        );
    }
}
