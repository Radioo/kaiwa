package com.example.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    private List<Message> messageHistory = new ArrayList<>();


    @MessageMapping("/hello")
    @SendTo("/topic/echo")
    public Message greeting(Message message) throws Exception {
        messageHistory.add(message);

        return message;
    }

    @MessageMapping("/history")
    @SendTo("/topic/history")
    public List<Message> history() throws Exception {
        return messageHistory;
    }
}
