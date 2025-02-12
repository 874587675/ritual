package com.ruoyi.project.business.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.ruoyi.project.business.util.config.WxPayConfig;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxMiniAppService {

    @Resource
    private WxPayConfig wxPayConfig;

    private WxMaService wxMaService;

    /**
     * 初始化 WxMaService
     */
    private WxMaService getWxMaService() {
        if (wxMaService == null) {
            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
            config.setAppid(wxPayConfig.getPayparams().getAppId());
            config.setSecret(wxPayConfig.getPayparams().getApiV3Key());
            wxMaService = new WxMaServiceImpl();
            wxMaService.setWxMaConfig(config);
        }
        return wxMaService;
    }

    /**
     * 通过 code 获取 openid 和 session_key
     *
     * @param code 小程序端获取的临时登录凭证(五分钟内有效)
     * @return 包含 openid 和 session_key 的对象
     * @throws WxErrorException 如果微信接口调用失败
     */
    public WxMaJscode2SessionResult getSessionInfo(String code) throws WxErrorException {
        return getWxMaService().getUserService().getSessionInfo(code);
    }
}
