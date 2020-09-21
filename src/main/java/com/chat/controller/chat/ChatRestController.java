package com.chat.controller.chat;

import com.chat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatRestController {

    @Qualifier("chatServiceImpl")
    @Autowired
    private ChatService service;

    @GetMapping("/chats")
    public List<String> getAllChats() {
        return service.getAllChats();
    }

    @PostMapping("/chat/{login}")
    public void createChat(@PathVariable("login") String login) {
        service.createChat(login);
    }
}
