package com.example.chat;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;

@RestController
class SSEController {
    private final SSEService service;

    SSEController(SSEService service) {
        this.service = service;
    }

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(Principal principal) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        service.addEmitter(new EmitterPrincipal(emitter, principal));
        return emitter;
    }

    @GetMapping(path = "/currentUsers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getCurrentUsers() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        service.addCurrentUsersEmitter(emitter);
        return emitter;
    }
}