package org.chench.springboot.scaffolding.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 处理文本消息
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.websocket.MyTextHandler
 * @date 2023.07.17
 */
public class MyTextHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(session.getId() + ": " + message.getPayload());
        session.sendMessage(new TextMessage("Hello, client: " + session.getId() + " msg: " + message.getPayload()));
        WebSocketSessionHandler.getInstance().saveSession(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 连接建立之后
        System.out.println("连接建立之后:");
        System.out.println(session.getUri().getQuery());
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 连接关闭之后
        System.out.println("连接关闭之后:");
        System.out.println(status.toString());
        super.afterConnectionClosed(session, status);
    }
}
