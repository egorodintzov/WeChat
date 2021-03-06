package com.chat.service.chat;

import com.chat.dao.ChatDao;
import com.chat.exceptions.ChatNotFoundException;
import com.chat.exceptions.UserNotFoundException;
import com.chat.model.Chat;
import com.chat.model.User;
import com.chat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private UserService service;

    @Autowired
    private ChatDao dao;

    /**
     * get chat by id
     *
     * @param id chat
     * @return chat
     */

    @Override
    public Chat findById(long id) {
        return dao.findById(id).orElseThrow(() ->
                new ChatNotFoundException("Chat not found")
        );
    }

    /**
     * get all chats of current user
     *
     * @return list names of chats
     */

    @Override
    public List<String> getAllChats(User currentUser) {

        // get all chats of current user
        Set<Chat> chats = currentUser.getChats();
        List<String> listUsers = new LinkedList<>();

        if (chats != null) {
            // going through all chats of current user
            for (Chat chat : chats) {

                // get user with index 1 from list with chat users, get user login and add to list
                listUsers.add(service.getUserByIndex(chat.getListUsers(), 1).getNickname());

            }
        }
        return listUsers;
    }

    /**
     * create chat
     *
     * @param user1
     * @param user2
     */

    @Override
    public void createChat(User user1, User user2) {
        if (user1 == null || user2 == null) {
            throw new UserNotFoundException("User not found");
        }
        Chat chat = new Chat();
        chat.setListUsers(new LinkedHashSet<>());

        Set<User> users = chat.getListUsers();
        users.add(user1);
        users.add(user2);


        for (User user : users) {

            user.getChats().add(chat);
            service.updateChats(user);

        }

    }

    @Override
    public void updateMessage(Chat chat) {
        Chat dbChat = findById(chat.getId());
        dbChat.setSetMessages(chat.getSetMessages());
        dao.save(dbChat);
    }
}
