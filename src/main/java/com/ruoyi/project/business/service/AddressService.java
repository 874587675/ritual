package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.Address;
import com.baomidou.mybatisplus.extension.service.IService;
public interface AddressService extends IService<Address>{


    String addAddress(Address address);

    String updateAddress(Address address);

    String deleteAddress(Integer id);
}
