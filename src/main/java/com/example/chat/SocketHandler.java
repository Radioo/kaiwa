package com.example.chat;

import com.example.chat.model.WebSocketTemplate;
import com.google.gson.Gson;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        sessions.add(session);
        sendConnectedUsers();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        sendConnectedUsers();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        WebSocketTemplate webSocketTemplate = new Gson().fromJson(message.getPayload(), WebSocketTemplate.class);
        String type = webSocketTemplate.type;
    }

    private void sendConnectedUsers() {
        WebSocketTemplate webSocketTemplate = new WebSocketTemplate();
        webSocketTemplate.type = "connectedUsers";
        webSocketTemplate.data = sessions.stream()
                .map(session -> Objects.requireNonNull(session.getPrincipal()).getName())
                .distinct()
                .collect(java.util.stream.Collectors.toList());
        String json = new Gson().toJson(webSocketTemplate);
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(json));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
