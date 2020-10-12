package com.chat.service.message;

import com.chat.dto.MessageContentDto;
import com.chat.dto.MessageDto;
import com.chat.model.Message;

public interface MessageService {

    void createMessage(MessageDto messageDto);

    void updateMessage(MessageContentDto message);

}
