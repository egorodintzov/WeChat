package com.chat.dto;

import com.chat.model.User;


public class OutputMessage {

    private User sender;
    private String text;
    private String date;

    public OutputMessage() {}

    public OutputMessage(User sender, String text, String date) {
        this.sender = sender;
        this.text = text;
        this.date = date;
    }

}
