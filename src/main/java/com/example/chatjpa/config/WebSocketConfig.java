package com.example.chatjpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration      //해당 클래스가 Bean의 설정
@EnableWebSocketMessageBroker  //enable our WebSocket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {   //웹 소켓 연결을 구성하기 위한 메서드를 구현하고 제공
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").setAllowedOrigins("http://localhost:3000").withSockJS();
    }

    /**
     * "/topic" - 1:N 구독방식
     * "/queue" - 1:1 구독방식 / 메세지 전달
     * @param registry
     */
    @Override
    //client 1 => client2로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic","/queue");
    }


}
