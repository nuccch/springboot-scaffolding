package org.chench.springboot.scaffolding.config;

import org.chench.springboot.scaffolding.websocket.SessionHandshakeInterceptor;
import org.chench.springboot.scaffolding.websocket.MyTextHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 配置WebSocket
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.WebSocketConfig
 * @date 2023.07.17
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 原生WebSocket API
        registry.addHandler(myHandler(), "/websocket/text")
                .addInterceptors(new SessionHandshakeInterceptor())   // 注册处理器
                .setAllowedOrigins("*");                                // 设置域范围

        // 使用SockJS
        registry.addHandler(myHandler(), "/sockjs/text")
                .addInterceptors(new SessionHandshakeInterceptor())   // 注册处理器
                .setAllowedOrigins("*")                                 // 设置域范围
                .withSockJS()                                           // 开启SockJS支持
                .setHeartbeatTime(10000);                               // 设置心跳时间间隔，默认25秒: 25000
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyTextHandler();
    }

    /**
     * 配置WebSocket引擎进行属性配置
     * @return
     */
    /*@Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }*/
}
