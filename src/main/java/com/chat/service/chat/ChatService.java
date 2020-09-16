package com.chat.service.chat;

import com.chat.dao.ChatDao;
import com.chat.dto.ChatDto;
import com.chat.dto.UserDto;
import com.chat.model.Chat;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Egor Odintsov
 */

@Service
public abstract class ChatService implements ChatServiceI {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService service;

    @Autowired
    private ChatDao dao;

    private static Logger logger = Logger.getLogger(ChatService.class.getName());

    /**
     * get chat by id
     * @param id chat
     * @return chat
     */

    public Chat findById(long id) {
        return dao.findById(id);
    }

    /**
     * get all chats of current user
     * @return list names of chats
     */

    @Override
    public List<String> getAllChats() {

        // get all chats of current user
        List<Chat> listChats = service.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getListChats();
        List<String> listUsers = new LinkedList<>();

        // going through all chats of current user
        for (Chat chat : listChats) {

            // get user with index 1 from list with chat users, get user login and add to list
            listUsers.add(chat.getListUsers().get(1).getLogin());

        }

        return listUsers;
    }

    /**
     * create chat
     * @param chatDto-object which contains list of users that will be added to chat
     */

    @Override
    public void createChat(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setListUsers(new LinkedList<>());
        List<User> listUsers = new LinkedList<>();

        // going through chat list with users
        for (UserDto dto : chatDto.getList()) {

            // get user by login
            User user = service.findByLogin(dto.getLogin());
            // and add to list
            listUsers.add(user);

            // add this user to list with chat users
            chat.getListUsers().add(user);

            if (user.getListChats() == null)
                user.setListChats(new LinkedList<>());
        }

        // going through list with users
        for (User user : listUsers) {

            // add this chat to list with user chats
            user.getListChats().add(chat);

            // update this user
            service.updateChats(user);
        }

        dao.save(chat);
    }

}
