package com.chat.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private String senderLogin;

    @ManyToMany(targetEntity = Chat.class,mappedBy = "chats",fetch = FetchType.LAZY)
    private List<Chat> listChats;

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

    public List<Chat> getListChats() {
        return listChats;
    }

    public void setListChats(List<Chat> listChats) {
        this.listChats = listChats;
    }
}
