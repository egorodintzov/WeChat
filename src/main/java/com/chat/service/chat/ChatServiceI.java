package com.chat.service.chat;

import com.chat.dto.ChatDto;

import java.util.List;

public interface ChatServiceI {

    List<String> getAllChats();

    void createChat(ChatDto chatDto);
}
