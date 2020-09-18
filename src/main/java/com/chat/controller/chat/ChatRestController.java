package com.chat.controller.chat;

import com.chat.dto.ChatDto;
import com.chat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

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

    @PostMapping("/{login}/chat")
    public void createChat(@PathVariable("login") String login,@Valid @RequestBody ChatDto chatDto) {
        service.createChat(chatDto);
    }
}
