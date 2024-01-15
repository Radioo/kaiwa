package com.example.chat;

import com.example.chat.model.MessageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SSEService {
    private final List<EmitterPrincipal> emitters = new CopyOnWriteArrayList<>();
    private final List<SseEmitter> currentUsersEmitters = new CopyOnWriteArrayList<>();
    private final HashSet<String> currentUsers = new HashSet<>();

    public List<EmitterPrincipal> getEmitters() {
        return emitters;
    }

    public void addEmitter(EmitterPrincipal emitter) {
        emitters.add(emitter);
        currentUsers.add(emitter.principal.getName());
        sendCurrentUsers();

        emitter.emitter.onCompletion(() -> {
            emitters.remove(emitter);
            currentUsers.remove(emitter.principal.getName());
            sendCurrentUsers();
        });
        emitter.emitter.onTimeout(() -> {
            emitters.remove(emitter);
            currentUsers.remove(emitter.principal.getName());
            sendCurrentUsers();
        });

        emitter.emitter.onError((e) -> {
            emitters.remove(emitter);
            currentUsers.remove(emitter.principal.getName());
            sendCurrentUsers();
        });
    }

    public void addCurrentUsersEmitter(SseEmitter emitter) {
        currentUsersEmitters.add(emitter);
        emitter.onCompletion(() -> currentUsersEmitters.remove(emitter));
        emitter.onTimeout(() -> currentUsersEmitters.remove(emitter));
        emitter.onError((e) -> currentUsersEmitters.remove(emitter));

        sendCurrentUsers();
    }

    public void sendMessage(MessageResponse message) {
        for (EmitterPrincipal emitter : emitters) {
            try {
                emitter.emitter.send(message);
            } catch (Exception e) {
                emitter.emitter.complete();
                emitters.remove(emitter);
            }
        }
    }

    private void sendCurrentUsers() {
        for(SseEmitter emitter : currentUsersEmitters) {
            List<String> aliveEmitters = new ArrayList<>();
            for(EmitterPrincipal ep : emitters) {
                try {
                    ep.emitter.send("test");
                    aliveEmitters.add(ep.principal.getName());
                }
                catch(Exception ignored) {

                }
            }

            try {
                emitter.send(aliveEmitters);
            } catch (Exception e) {
                emitter.complete();
                currentUsersEmitters.remove(emitter);
            }
        }
    }
}
