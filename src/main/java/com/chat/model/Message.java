package com.chat.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private String senderLogin;

    @ManyToMany(targetEntity = Chat.class,fetch = FetchType.LAZY)
    private Set<Chat> setChats;

    public Message() {}

    public Message(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSenderLogin() {
        return senderLogin;
    }

    public void setSenderLogin(String senderLogin) {
        this.senderLogin = senderLogin;
    }

    public Set<Chat> getSetChats() {
        return setChats;
    }

    public void setSetChats(Set<Chat> setChats) {
        this.setChats = setChats;
    }
}
