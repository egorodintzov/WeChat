package com.chat.controller.chat;

import com.chat.dto.MessageDto;
import com.chat.dto.OutputMessage;
import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.service.chat.ChatService;
import com.chat.service.message.MessageService;
import com.chat.service.user.UserService;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class ChatController {


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;



    @Autowired
    private MessageService service;

    @Autowired
    private UserService userService;

    @MessageMapping("/message")
    public void sendMessageToTopic(MessageDto messageDto) {
        service.createMessage(messageDto);

        OutputMessage out = new OutputMessage(
                userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()),
                messageDto.getMessage(),
                new SimpleDateFormat("HH:mm").format(new Date()));

        simpMessagingTemplate.convertAndSendToUser(Long.toString(messageDto.getIdChat()),"/chat/topic",out);
    }

    @GetMapping("/")
    public String chat() {
        return "chats";
    }

    @GetMapping("/chat")
    public String chats() {
        return "chat";
    }

}
