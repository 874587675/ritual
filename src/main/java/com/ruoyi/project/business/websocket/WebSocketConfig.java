package com.ruoyi.project.business.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    //registerWebSocketHandlers 方法是你需要实现的接口方法，它的作用是配置哪些路径的 WebSocket 请求应该由哪些 WebSocket 处理器来处理。
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(), "/websocket/chat")
                .addInterceptors(new WebSocketHandshakeInterceptor())
                .setAllowedOrigins("*");
        
    }
    
    @Bean
    public WebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler();
    }
    
    
}
