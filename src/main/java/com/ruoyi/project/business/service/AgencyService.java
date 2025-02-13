package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.Agency;
import com.baomidou.mybatisplus.extension.service.IService;
public interface AgencyService extends IService<Agency>{


    String insertAgencyInfo(Agency agency);
}
