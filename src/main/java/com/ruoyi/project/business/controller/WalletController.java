package com.ruoyi.project.business.controller;

import com.ruoyi.project.business.domain.Wallet;
import com.ruoyi.project.business.service.WalletService;
import com.ruoyi.project.business.service.impl.WalletServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;


@RestController
@Api(tags = "钱包信息模块管理")
@RequestMapping("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;


}
