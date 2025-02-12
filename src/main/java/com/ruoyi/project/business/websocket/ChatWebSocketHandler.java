package com.ruoyi.project.business.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ChatWebSocketHandler extends TextWebSocketHandler {

    // 维护用户和 WebSocket 会话的映射
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String roomId = (String) session.getAttributes().get("roomId");
        log.info("收到房间 {} 的消息: {}", roomId, payload);
        // 将消息广播给同一房间的所有用户
        for (WebSocketSession webSocketSession : sessions.values()) {
            if (webSocketSession.isOpen() && webSocketSession.getAttributes().get("roomId").equals(roomId)) {
                webSocketSession.sendMessage(new TextMessage(payload));
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = (String) session.getAttributes().get("roomId");
        String userId = (String) session.getAttributes().get("userId");
        log.info("用户 {} 加入 房间号 {}", userId, roomId);
        sessions.put(userId, session);
        
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = (String) session.getAttributes().get("roomId");
        String userId = (String) session.getAttributes().get("userId");
        log.info("用户 {} 离开房间 {}", userId, roomId);
        sessions.remove(userId);
    }

    public static void disconnectUser(String userId) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null) {
            session.close(CloseStatus.NORMAL); // 关闭连接
        }
    }

    public static void disconnectAllUsers() throws Exception {
        // 遍历所有的 WebSocketSession 并关闭连接
        for (WebSocketSession session : sessions.values()) {
            if (session.isOpen()) {
                session.close(CloseStatus.NORMAL); // 关闭连接
            }
        }
        sessions.clear(); // 清空所有会话记录
        log.info("所有用户的连接已断开！");
    }
}
