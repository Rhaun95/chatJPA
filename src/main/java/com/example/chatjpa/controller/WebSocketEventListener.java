package com.example.chatjpa.controller;

import com.example.chatjpa.model.ChatMessage;
import com.example.chatjpa.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


/**
 * 소켓 연결(socket connect) 그리고 소켓 연결 끊기(disconnect) 이벤트를 수신하여
 * 사용자가 채팅방을 참여(JOIN)하거나 떠날때(LEAVE)의 이벤트를 logging 하거나 broadcast
 */
@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    //addUser()메서드에서 사용자 참여 이벤트를  broadcast 함
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");

    }

    //웹 소켓 세션에서 사용자 이름을 추출하고 연결된 모든 클라이언트에게 사용자 퇴장 이벤트를 broadcast 해줌
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        //controller의 addUser()에서 저장한 username을 가져옴
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            logger.info("User Disconnected : " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
