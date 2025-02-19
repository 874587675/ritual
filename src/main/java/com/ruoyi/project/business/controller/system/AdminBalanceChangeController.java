package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.BalanceChange;
import com.ruoyi.project.business.service.BalanceChangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-余额变动信息管理模块")
@RequestMapping("/system/balanceChange")
public class AdminBalanceChangeController {

    @Resource
    private BalanceChangeService balanceChangeService;

    @ApiOperation("分页查询余额记录变动表")
    @GetMapping("/selectAllBalanceChanges")
    public R<IPage<BalanceChange>> selectAllBalanceChanges(@RequestParam(defaultValue = "1") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam Integer walletId,
                                                           @RequestParam(required = false) String queryDate) {
        return R.ok(balanceChangeService.selectAllBalanceChanges(pageNo, pageSize, walletId, queryDate));
    }

    
}
