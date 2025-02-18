package com.ruoyi.project.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.business.domain.Address;
import com.ruoyi.project.business.mapper.AddressMapper;
import com.ruoyi.project.business.service.AddressService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    @Transactional
    public String addAddress(Address address) {
        try {
            if (address.getIsDefault() == 1) {
                // 若是默认地址，先将原来的默认地址设为非默认
                addressMapper.update(new LambdaUpdateWrapper<Address>().set(Address::getIsDefault, 0).eq(Address::getUserId, address.getUserId()));
            }
            int insertResult = addressMapper.insert(address);
            if (insertResult <= 0) {
                log.error("添加收货地址失败");
                throw new RuntimeException("添加收货地址失败");
            }
            log.info("添加收货地址成功");
            return "添加收货地址成功";
        } catch (Exception e) {
            log.error("添加收货地址失败, 错误原因:{}", e.getMessage());
            throw new RuntimeException("添加收货地址失败");
        }
    }

    @Override
    public String updateAddress(Address address) {
        try {
            if (address.getIsDefault() == 1) {
                // 若是默认地址，先将原来的默认地址设为非默认
                addressMapper.update(new LambdaUpdateWrapper<Address>().set(Address::getIsDefault, 0).eq(Address::getUserId, address.getUserId()));
            }
            int updateResult = addressMapper.updateById(address);
            if (updateResult <= 0) {
                log.error("更新收货地址失败");
                throw new RuntimeException("更新收货地址失败");
            }
            log.info("更新收货地址成功");
            return "更新收货地址成功";
        } catch (Exception e) {
            log.error("更新收货地址失败, 错误原因:{}", e.getMessage());
            throw new RuntimeException("更新收货地址失败");
        }
    }

    @Override
    public String deleteAddress(Integer id) {
        try{
            Address address = addressMapper.selectById(id);
            if (address == null) {
                log.error("该收货地址不存在");
                throw new ServiceException("该收货地址不存在");
            }else {
                int deleteResult = addressMapper.update(new LambdaUpdateWrapper<Address>().eq(Address::getId,id).set(Address::getIsDeleted,1));
                if (deleteResult <= 0) {
                    log.error("删除收货地址失败");
                    throw new RuntimeException("删除收货地址失败");
                }
                log.info("删除收货地址成功");
                return "删除收货地址成功";
            }
        }catch(Exception e){
            log.error("删除收货地址失败, 错误原因:{}", e.getMessage());
            throw new RuntimeException("删除收货地址失败");
        }
        
    }
}
