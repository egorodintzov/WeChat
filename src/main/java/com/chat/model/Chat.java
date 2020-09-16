package com.chat.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = User.class,fetch=FetchType.LAZY)
    @JoinTable(name="user_chat_table",
            // column which contains id chats all users which stored at this list
            joinColumns = {@JoinColumn(name="chats")},
            inverseJoinColumns = {@JoinColumn(name = "users")})
    private List<User> listUsers;

    @ManyToMany(targetEntity = Message.class)
    private List<Message> listMessages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(List<Message> listMessages) {
        this.listMessages = listMessages;
    }
}
