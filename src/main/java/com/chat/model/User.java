package com.chat.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false)
    private String login;
    @Column(nullable=false)
    private String password;

    @OneToOne(targetEntity = Photo.class,cascade=CascadeType.REMOVE,orphanRemoval = true)
    private Photo photo;

    @ManyToMany(targetEntity = Chat.class,fetch = FetchType.LAZY)
    @JoinTable(name="users_chats",
               // column contains id users all chats which stored at this list
               joinColumns = {@JoinColumn(name="user_id")},
               // column contains id chats all users which stored at first column
               inverseJoinColumns = {@JoinColumn(name = "chat_id")})
    private Set<Chat> chats;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {}

    public User(String login,String password,Role role) {
        this.login=login;
        this.password=password;
        this.role=role;
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

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
