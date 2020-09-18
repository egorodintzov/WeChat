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

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService service;

    @Autowired
    private ChatDao dao;

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
        Set<Chat> chats = service.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getChats();
        List<String> listUsers = new LinkedList<>();

        // going through all chats of current user
        for (Chat chat : chats) {

            // get user with index 1 from list with chat users, get user login and add to list
            listUsers.add(service.getUserByIndex(chat.getListUsers(),1).getLogin());

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
        chat.setListUsers(new LinkedHashSet<>());
        Set<User> listUsers = new LinkedHashSet<>();

        // going through chat list with users
        for (UserDto dto : chatDto.getList()) {

            // get user by login
            User user = service.findByLogin(dto.getLogin());
            // and add to list
            listUsers.add(user);

            // add this user to list with chat users
            chat.getListUsers().add(user);

            if (user.getChats() == null)
                user.setChats(new LinkedHashSet<>());
        }

        // going through list with users
        for (User user : listUsers) {

            // add this chat to list with user chats
            user.getChats().add(chat);

            // update this user
            service.updateChats(user);
        }

        dao.save(chat);
    }
}
