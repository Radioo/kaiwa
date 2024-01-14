package com.example.chat;

import com.example.chat.model.Message;
import com.example.chat.model.MessageResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SSEService {
    private final List<EmitterPrincipal> emitters = new CopyOnWriteArrayList<>();

    public List<EmitterPrincipal> getEmitters() {
        return emitters;
    }

    public void addEmitter(EmitterPrincipal emitter) {
        emitters.add(emitter);
        emitter.emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.emitter.onTimeout(() -> emitters.remove(emitter));
    }

    public void sendMessage(MessageResponse message) {
        for (EmitterPrincipal emitter : emitters) {
            try {
                emitter.emitter.send(message);
            } catch (IOException e) {
                emitter.emitter.complete();
                emitters.remove(emitter);
            }
        }
    }
}
