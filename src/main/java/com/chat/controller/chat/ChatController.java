package com.chat.controller.chat;

import com.chat.dto.MessageDto;
import com.chat.model.Message;
import com.chat.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @Autowired
    private MessageService service;

    @MessageMapping("/message")
    @SendTo("/chat/topic")
    public MessageDto sendMessageToTopic(MessageDto message) {
        service.createMessage(new Message(message.getMessage()));
        return message;
    }

    @GetMapping("/")
    public String chat() {
        return "chats";
    }

}
