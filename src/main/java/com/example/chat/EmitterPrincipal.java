package com.example.chat;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;

public class EmitterPrincipal {
    public SseEmitter emitter;
    public Principal principal;

    public EmitterPrincipal(SseEmitter emitter, Principal principal) {
        this.emitter = emitter;
        this.principal = principal;
    }
}
