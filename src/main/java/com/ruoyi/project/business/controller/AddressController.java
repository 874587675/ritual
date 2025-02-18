package com.ruoyi.project.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.business.domain.Address;
import com.ruoyi.project.business.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api(tags = "收货地址信息管理模块")
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @ApiOperation("分页查询个人的收货地址")
    @GetMapping("/selectAddressByUserId")
    public R<IPage<Address>> selectAddressByUserId(@RequestParam(defaultValue = "1") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam String userId) {
        return R.ok(addressService.page(new Page<>(pageNo, pageSize), new LambdaQueryWrapper<Address>().eq(Address::getUserId, userId).eq(Address::getIsDeleted,0)));
    }
    
    @ApiOperation("添加新的收货地址")
    @PostMapping("/addAddress")
    public R<String> addAddress(@RequestBody Address address){
        return R.ok(addressService.addAddress(address));
    }
    
    @ApiOperation("修改收货地址")
    @PostMapping("/updateAddress")
    public R<String> updateAddress(@RequestBody Address address){
        return R.ok(addressService.updateAddress(address));
    }
    
    @ApiOperation("删除收货地址(逻辑删除)")
    @PostMapping("/deleteAddress")
    public R<String> deleteAddress(@RequestParam Integer id){
        return R.ok(addressService.deleteAddress(id));
    }
}
