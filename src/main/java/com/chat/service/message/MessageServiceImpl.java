package com.chat.service.message;

import com.chat.dao.MessageDao;
import com.chat.dto.MessageContentDto;
import com.chat.dto.MessageDto;
import com.chat.exceptions.MessageNotFoundException;
import com.chat.model.Chat;
import com.chat.model.Message;
import com.chat.service.chat.ChatService;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao dao;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;


    /**
     * create message
     *
     * @param messageDto object
     */

    @Override
    public void createMessage(MessageDto messageDto) {
        Chat chat = chatService.findById(messageDto.getIdChat());

        Message message = new Message();

        message.setMessage(messageDto.getMessage());
        message.setUser(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        message.setSetChats(new LinkedHashSet<>());
        message.getSetChats().add(chat);

        if(chat.getSetMessages()==null) {
            chat.setSetMessages(new LinkedHashSet<>());
        }
        chat.getSetMessages().add(message);

        chatService.updateMessage(chat);

    }

    public Message findById(long id) {
        return dao.findById(id).orElseThrow(() ->
            new MessageNotFoundException("Message not found")
        );
    }

    /**
     * update message
     *
     * @param message (MessageContentDto) object which contains new message
     */

    @Override
    public void updateMessage(MessageContentDto message) {
        Message dbMessage = findById(message.getId());
        dbMessage.setMessage(message.getMessage());
        dao.save(dbMessage);
    }
}
