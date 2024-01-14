package com.example.chat.model;

import lombok.Getter;

import java.util.List;

@Getter
public class MessageResponse {
    private Long id;
    private String user;
    private String text;
    private String date;
    private List<String> connectedUsers;

    public MessageResponse() {
    }

    public MessageResponse(Long id, String user, String text, String date, List<String> connectedUsers) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.date = date;
        this.connectedUsers = connectedUsers;
    }
}
