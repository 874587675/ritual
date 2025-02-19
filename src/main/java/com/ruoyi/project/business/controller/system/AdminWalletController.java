package com.ruoyi.project.business.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Wallet;
import com.ruoyi.project.business.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "管理端-钱包信息模块管理")
@RequestMapping("/system/wallet")
public class AdminWalletController {

    @Resource
    private WalletService walletService;

    @ApiOperation("查询所有用户的钱包信息")
    @GetMapping("/selectAll")
    public R<IPage<Wallet>> selectAll(@RequestParam(defaultValue = "1") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        return R.ok(walletService.page(new Page<>(pageNo,pageSize)));
    }

    @ApiOperation("查询个人钱包的余额情况")
    @GetMapping("/selectWalletByUserId")
    public R<Wallet> selectWalletByUserId(@RequestParam String userId){
        return R.ok(walletService.selectWalletByUserId(userId));
    }
}
