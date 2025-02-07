package com.ruoyi.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            // 如果允许所有来源，可以使用 "*"，否则限制具体来源
            .allowedOriginPatterns("*")  
            .allowCredentials(true)  // 是否允许发送 Cookie 等凭证信息
            // 指定允许的 HTTP 方法
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            // 指定允许的请求头
            .allowedHeaders("Content-Type", "Authorization", "Accept", "Origin", "X-Requested-With")
            // 最大预检请求的缓存时间，单位为秒
            .maxAge(3600);
            // 如果你需要允许客户端携带 cookies，且你不希望全局放行，可以限制域名
            // .allowedOrigins("https://yourdomain.com", "https://anotherdomain.com");
            // 也可以限制请求的 Content-Type
            // .allowedHeaders("Content-Type");
    }
}
