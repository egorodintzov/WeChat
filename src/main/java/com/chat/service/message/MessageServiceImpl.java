package com.chat.service.message;

import com.chat.dao.MessageDao;
import com.chat.dto.MessageContentDto;
import com.chat.exceptions.MessageNotFoundException;
import com.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao dao;


    /**
     * create message
     *
     * @param message object which will be create
     */

    @Override
    public void createMessage(Message message) {
        dao.save(message);
    }

    public Message findById(long id) {
        return dao.findById(id).orElseThrow(() -> {
            throw new MessageNotFoundException("Message not found");
        });
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
