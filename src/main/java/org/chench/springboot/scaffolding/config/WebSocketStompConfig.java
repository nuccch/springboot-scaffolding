package org.chench.springboot.scaffolding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 在Spring中启动STOMP
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.WebSocketStompConfig
 * @date 2023.07.17
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketStompConfig.class);

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp")
                //.addInterceptors(new SessionHandshakeInterceptor())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                LinkedMultiValueMap<String, Object> headers = (LinkedMultiValueMap<String, Object>) message.getHeaders().get("nativeHeaders");
                String sessionId = message.getHeaders().get("simpSessionId").toString();
                if(headers != null && headers.get("token") != null) {
                    Object token = headers.get("token").get(0);
                    System.out.println("token: " + token);
                }
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // 建立连接：获取token进行验证
                    logger.info("connected, session id: " + sessionId);
                }else if(StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    // 断开连接:
                    logger.info("disconnect, session id: " + sessionId);
                }

                return super.preSend(message, channel);
            }
        });
    }


}
