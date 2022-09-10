package com.example.chatjpa.model;


import lombok.Data;

/**
 * 챗의 타입, 내용, 송신자
 */
@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    public MessageType getType() {
        return type;
    }
}
