package com.chat.controller.chat;

import com.chat.service.chat.ChatService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatRestController {

    @Autowired
    private ChatService service;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<String> getAllChats() {
        return service.getAllChats();
    }

    @PostMapping("/{login}")
    public void createChat(@PathVariable("login") String login) {
        service.createChat(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()),userService.findByLogin(login));
    }
}
