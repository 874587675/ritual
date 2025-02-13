package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.UserOption;
import com.baomidou.mybatisplus.extension.service.IService;
public interface UserOptionService extends IService<UserOption>{
    
    String insertUserOption(UserOption userOption);
}
