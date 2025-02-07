package com.ruoyi.project.business.util.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliyunConfig {
    private AccessParams accessparams;
    private SmsParams smsparams;
    private OcrParams ocrparams;

    @Data
    public static class AccessParams {
        private String KeyId;
        private String KeySecret;
    }

    @Data
    public static class SmsParams {
        private String UserPrincipalName;
        private String Password;
        private String SignName;
        private String Endpoint;
        private String TemplateCode;
        private String RegionId;
    }

    @Data
    public static class OcrParams {
        private String Endpoint;
        private String RegionId;
    }
}
