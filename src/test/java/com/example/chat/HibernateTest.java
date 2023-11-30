package com.example.chat;

import com.example.chat.model.User;
import com.example.chat.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class HibernateTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() {
        userRepository.save(new User("admin", (new BCryptPasswordEncoder()).encode("hunter2")));

        User user = userRepository.findByName("admin");
        assert user != null;
    }
}
