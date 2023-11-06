package com.example.chat.model;

import lombok.Getter;

@Getter
public class Message {
    private String user;
    private String message;
    private String timestamp;

    public void setUser(String user) {
        this.user = user;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
