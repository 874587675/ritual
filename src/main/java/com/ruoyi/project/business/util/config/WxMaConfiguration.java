package com.ruoyi.project.business.util.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Configuration
@Getter
public class WxMaConfiguration {
    
    @Resource
    private WxPayConfig wxPayConfig;
    
    

    @Bean
    public WxMaService userWxMaService() {
        WxMaService maService = new WxMaServiceImpl();
        Map<String, WxMaConfig> wxMaConfigMap = new HashMap<>();
        // 用户端小程序配置
        WxMaDefaultConfigImpl userConfig = new WxMaDefaultConfigImpl();
        userConfig.setAppid(wxPayConfig.getPayparams().getAppId());
        userConfig.setSecret(wxPayConfig.getPayparams().getApiV3Key());
        wxMaConfigMap.put(userConfig.getAppid(), userConfig);
          if(userConfig.getAppid() != null){
              maService.setMultiConfigs(wxMaConfigMap);
              maService.setWxMaConfig(userConfig);
              return maService;
          }
        maService.setMultiConfigs(wxMaConfigMap);
        return maService;
    }
}
