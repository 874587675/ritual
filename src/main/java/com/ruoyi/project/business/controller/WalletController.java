package com.ruoyi.project.business.controller;

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
import java.util.Date;


@RestController
@Api(tags = "钱包信息模块管理")
@RequestMapping("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    @ApiOperation("查询个人钱包的余额情况")
    @GetMapping("/selectWalletByUserId")
    public R<Wallet> selectWalletByUserId(@RequestParam String userId){
        return R.ok(walletService.selectWalletByUserId(userId));
    }
}
