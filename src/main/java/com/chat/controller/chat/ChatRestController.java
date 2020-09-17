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
@RequestMapping("/c")
public class ChatRestController {

    @Qualifier("chatServiceImpl")
    @Autowired
    private ChatService service;

    @GetMapping("/allChats")
    public List<String> getAllChats() {
        return service.getAllChats();
    }

    @PostMapping("/chat")
    public void createChat(@Valid @RequestBody ChatDto chatDto) {
        service.createChat(chatDto);
    }
}
