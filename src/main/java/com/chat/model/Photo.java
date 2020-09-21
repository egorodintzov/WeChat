package com.chat.model;

import javax.persistence.*;

@Entity
@Table(name="photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String contentType;
    private byte[] content;

    @OneToOne(targetEntity = User.class,fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
    private User user;

    public Photo() {}

    public Photo(byte[] content,String name,String contentType) {
        this.content=content;
        this.name=name;
        this.contentType=contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
