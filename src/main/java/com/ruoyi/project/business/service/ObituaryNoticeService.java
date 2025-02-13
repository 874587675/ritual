package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.ObituaryNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;

public interface ObituaryNoticeService extends IService<ObituaryNotice>{
    ObituaryNoticeVO selectObituaryNoticeByUserId(Integer pageNo, Integer pageSize, String userId);

    String deleteObituaryNotice(Integer id);
}
