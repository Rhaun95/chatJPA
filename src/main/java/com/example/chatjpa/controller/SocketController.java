package com.example.chatjpa.controller;

import com.example.chatjpa.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Component
public class SocketController {


//    private static Set<Integer> userList= new HashSet<>();
////
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;

    //SimpMessagingTemplate을 이용해서 convertAndSend를 통해서 특정아이디를 이용한 주소로 메세지를 보내주었다.
//
//    @MessageMapping("/chat/{id}")
//    public void sendMessage(@Payload MessageDTO messageDTO, @DestinationVariable Integer id){
//        this.simpMessagingTemplate.convertAndSend("/queue/addChatToClient/" + id, messageDTO);
//    }
//
//    /**
//     * @Payload : header와 메타데이터를 같이 보냄
//     * @param userId
//     */
//    @MessageMapping("/join")
//    public void joinUser(@Payload Integer userId){
//        userList.add(userId);
//        userList.forEach(user-> System.out.println(user));
////    }
    @MessageMapping("/chat.sendMessage")  //이 앞에 /app이 붙음
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }



}
