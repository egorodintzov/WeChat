package com.chat.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;

    @OneToOne(targetEntity = Photo.class)
    private Photo photo;

    @ManyToMany(targetEntity = Chat.class,fetch = FetchType.LAZY)
    @JoinTable(name="user_chat_table",
               // column which contains id users all chats which stored at this list
               joinColumns = {@JoinColumn(name="users")},
               inverseJoinColumns = {@JoinColumn(name = "chats")})
    private List<Chat> listChats;

    @OneToMany(targetEntity = Message.class)
    private List<Message> listMessages;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {}

    public User(String login,String password) {
        this.login=login;
        this.password=password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Chat> getListChats() {
        return listChats;
    }

    public void setListChats(List<Chat> listChats) {
        this.listChats = listChats;
    }

    public List<Message> getListMessages() {
        return listMessages;
    }

    public void setListMessages(List<Message> listMessages) {
        this.listMessages = listMessages;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
