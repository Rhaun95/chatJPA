package com.example.chatjpa.dto;

import com.example.chatjpa.entity.Conversation;
import com.example.chatjpa.entity.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "message_id")
        private Integer id;

        @NotNull
        private String content;

        @NotNull
        @Column(name = "image_name")
        private String imageName;

        @NotNull
        @Column(name= "image_url")
        private String imageUrl;


}
