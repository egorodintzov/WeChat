package com.chat.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY, mappedBy = "users")
    private Set<User> listUsers;

    @ManyToMany(targetEntity = Message.class, fetch = FetchType.LAZY)
    @JoinTable(name = "chat_messages",
               joinColumns = {@JoinColumn(name = "chat_id")},
               inverseJoinColumns = {@JoinColumn(name = "message_id")})
    private List<Message> listMessages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Set<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(List<Message> listMessages) {
        this.listMessages = listMessages;
    }
}
