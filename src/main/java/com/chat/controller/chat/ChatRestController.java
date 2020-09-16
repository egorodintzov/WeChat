package com.chat.controller.chat;

import com.chat.dto.ChatDto;
import com.chat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ChatRestController {

    Logger logger = Logger.getLogger(ChatRestController.class.getName());

    @Qualifier("chatServiceImpl")
    @Autowired
    private ChatService service;

    @GetMapping("/allChats")
    public List<String> getAllChats() {
        return service.getAllChats();
    }

    @PostMapping("/chat")
    public void createChat(@RequestBody ChatDto chatDto) {
        logger.info(chatDto.getList() + "");
        service.createChat(chatDto);
    }
}
