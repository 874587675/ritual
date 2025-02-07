package com.ruoyi.project.business.util.config;

import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "wxpay")
@Data
public class WxPayConfig {
    private PayParams payparams;
    // SpringBoot的Bean默认是单例的
    @Bean
    public RSAAutoCertificateConfig getWxPayConfig() {
        return new RSAAutoCertificateConfig.Builder()
                //商户号
                .merchantId(payparams.getMerchantId())
                //商户API私钥路径
                .privateKeyFromPath(payparams.getPrivateKeyPath())
                //商户证书序列号
                .merchantSerialNumber(payparams.getMerchantSerialNumber())
                //商户APIV3密钥
                .apiV3Key(payparams.getApiV3Key())
                .build();
    }
    
    @Data
    public static class PayParams {
        private String MerchantId;
        private String AppId;
        private String PrivateKeyPath;
        private String PublicCertPath;
        private String MerchantSerialNumber;
        private String ApiV3Key;
        private String NotifyUrl;
        private String ReturnUrl;
    }
}
