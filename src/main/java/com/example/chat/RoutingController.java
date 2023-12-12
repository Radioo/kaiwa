package com.example.chat;

import com.example.chat.model.Message;
import com.example.chat.model.MessageResponse;
import com.example.chat.model.RegisterForm;
import com.example.chat.model.User;
import com.example.chat.repository.MessageRepository;
import com.example.chat.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RoutingController {
    private final SSEService service;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public RoutingController(SSEService service, MessageRepository messageRepository, UserRepository userRepository) {
        this.service = service;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
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

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody RegisterForm form) {
        User existing = userRepository.findByName(form.getName());
        if(existing != null) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User(form.getName(), new BCryptPasswordEncoder().encode(form.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody String message, Principal principal) {
        User user = userRepository.findByName(principal.getName());

        Message newMessage = new Message(user, message);
        newMessage = messageRepository.save(newMessage);

        service.sendMessage(new MessageResponse(
                newMessage.getId(),
                newMessage.getUser().getName(),
                newMessage.getText(),
                newMessage.getParsedDate()
        ));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/history")
    @ResponseBody
    public List<MessageResponse> getMessageHistory() {
        List<Message> messages = messageRepository.findLast50ByOrderByIdDesc();

        return messages.stream()
                .map(message -> new MessageResponse(
                        message.getId(),
                        message.getUser().getName(),
                        message.getText(),
                        message.getParsedDate()
                ))
                .collect(Collectors.toList());
    }
}
