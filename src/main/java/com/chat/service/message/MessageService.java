package com.chat.service.message;

import com.chat.dao.MessageDao;
import com.chat.dto.MessageContentDto;
import com.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * @author Egor Odintsov
 */

@Service
public abstract class MessageService implements MessageServiceI {

    @Autowired
    private MessageDao dao;

    private static Logger logger = Logger.getLogger(MessageService.class.getName());

    /**
     * create message
     *
     * @param message object which will be create
     */

    @Override
    public void createMessage(Message message) {
        logger.info(message + "");
        dao.save(message);
    }

    /**
     * update message
     *
     * @param message (MessageContentDto) object which contains new message
     */

    @Override
    public void updateMessage(MessageContentDto message) {
        logger.info("new message: " + message.getMessage());
        Message dbMessage = dao.findById(message.getId());
        logger.info("old message: " + dbMessage.getMessage());
        dbMessage.setMessage(message.getMessage());
        dao.save(dbMessage);
    }

}
