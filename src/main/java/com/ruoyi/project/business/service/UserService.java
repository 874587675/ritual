package com.ruoyi.project.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.domain.User;


public interface UserService extends IService<User> {
    String loginByUsername(String username, String password, String code, String uuid);

    String loginByPhone(String phone, String code);

    String registerByUsername(String username, String password, String code, String uuid);
    
    String registerByPhone(String phone, String password,String code);

    String registerByEmail(String email, String password, String code);

    Boolean checkUsername(String username);
    
    Boolean checkPhone(String phone);

    Boolean checkEmail(String email);

   
}
