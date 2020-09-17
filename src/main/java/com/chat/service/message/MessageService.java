package com.chat.service.message;

import com.chat.dto.MessageContentDto;
import com.chat.model.Message;

public interface MessageService {

    void createMessage(Message message);

    void updateMessage(MessageContentDto message);

}
