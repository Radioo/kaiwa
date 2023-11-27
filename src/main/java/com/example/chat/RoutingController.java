package com.example.chat;

import com.example.chat.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RoutingController {
    private final SSEService service;

    public RoutingController(SSEService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        model.addAttribute("route", "");

        return "index";
    }

    @GetMapping("/chat")
    public String showChatPage(Model model) {
        model.addAttribute("route", "chat");

        return "index";
    }

    @GetMapping("/username")
    @ResponseBody
    public String getUserName(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody Message message) {
        service.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}
