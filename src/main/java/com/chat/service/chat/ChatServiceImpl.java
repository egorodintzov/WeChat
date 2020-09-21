package com.chat.service.chat;

import com.chat.dao.ChatDao;
import com.chat.exceptions.ChatNotFoundException;
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
        return dao.findById(id).orElseThrow(() -> {
            throw new ChatNotFoundException("Chat not found");
        });
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

        if(chats!=null) {
            // going through all chats of current user
            for (Chat chat : chats) {

                // get user with index 1 from list with chat users, get user login and add to list
                listUsers.add(service.getUserByIndex(chat.getListUsers(), 1).getLogin());

            }
        }
        return listUsers;
    }

    /**
     * create chat
     *
     * @param login
     */

    @Override
    public void createChat(String login) {
        Chat chat = new Chat();
        chat.setListUsers(new LinkedHashSet<>());

        Set<User> setUsers = chat.getListUsers();
        setUsers.add(service.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        setUsers.add(service.findByLogin(login));

        for(User user : setUsers) {

            user.getChats().add(chat);
            service.updateChats(user);

        }

        dao.save(chat);
    }
}
