package com.ruoyi.project.business.websocket;

import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String query = request.getURI().getQuery();
        if (query != null && !query.isEmpty()) {
            String[] params = query.split("&");
            String userId = null;
            String roomId = null;

            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    if ("userId".equals(keyValue[0])) {
                        userId = keyValue[1];
                    } else if ("roomId".equals(keyValue[0])) {
                        roomId = keyValue[1];
                    }
                }
            }

            // 将获取到的参数存入 attributes
            attributes.put("userId", userId);
            attributes.put("roomId", roomId);
        } else {
            // 处理查询字符串为空的情况，或者记录日志
            log.error("查询字符串为空，无法获取 userId 和 roomId");
            throw new ServiceException("无法获取 用户编号 和 房间编号");
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }
}
