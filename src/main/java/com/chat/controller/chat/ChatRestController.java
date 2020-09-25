package com.chat.controller.chat;

import com.chat.model.User;
import com.chat.service.chat.ChatService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        final User CURRENT_USER = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return service.getAllChats(CURRENT_USER);
    }

    @PostMapping("/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createChat(@PathVariable("id") long id) {
        final User CURRENT_USER = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        service.createChat(CURRENT_USER,userService.findById(id));
    }
}
