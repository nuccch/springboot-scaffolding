package org.chench.springboot.scaffolding.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chench
 * @desc org.chench.springboot.scaffolding.websocket.WebSocketSessionHandler
 * @date 2023.07.17
 */
public class WebSocketSessionHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketSessionHandler.class);
    private static final WebSocketSessionHandler instance = new WebSocketSessionHandler();
    private WebSocketSessionHandler() {}
    private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>(0);

    public static WebSocketSessionHandler getInstance() {
        return instance;
    }

    public void saveSession(WebSocketSession session) {
        this.sessions.add(session);
    }

    public void sendMsg(String msg) {
        Iterator<WebSocketSession> it = sessions.iterator();
        while(it.hasNext()) {
            WebSocketSession session = it.next();
            try {
                session.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                logger.error("send msg error: {}, {}", session, msg);
                e.printStackTrace();
                try {
                    // 关闭会话,同时将session对象移除集合
                    session.close();
                    //sessions.remove(session);
                    it.remove();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
