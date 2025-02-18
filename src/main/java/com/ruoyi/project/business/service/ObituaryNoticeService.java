package com.ruoyi.project.business.service;

import com.ruoyi.project.business.domain.ObituaryNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.business.pay.wechat.vo.WeChatJsapiPayVO;
import com.ruoyi.project.business.vo.ObituaryNoticeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ObituaryNoticeService extends IService<ObituaryNotice>{
    ObituaryNoticeVO selectObituaryNoticeByUserId(Integer pageNo, Integer pageSize, String userId);

    String deleteObituaryNotice(Integer id);

    String addObituaryNotice(ObituaryNoticeVO obituaryNoticeVO);

    ObituaryNoticeVO selectObituaryNoticeById(Integer id);

    Map<String, String> orderObituaryNotice(WeChatJsapiPayVO weChatJsapiPayVO);

    String payObituaryNoticeCallback(HttpServletRequest request);

    ObituaryNoticeVO queryObituaryNoticeForGiftItems(Integer id);
}
