package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);


        String interfaceName = "wlan0";
        String ipAddress = serverIP.getIPAddress(interfaceName);

    }

}
