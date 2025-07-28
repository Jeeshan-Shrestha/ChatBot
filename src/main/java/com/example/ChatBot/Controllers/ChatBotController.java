package com.example.ChatBot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatBot.Models.UserMessage;
import com.example.ChatBot.Services.ChatBotServices;


@RestController
public class ChatBotController {

    @Autowired
    ChatBotServices chatBotServices;
    
    @PostMapping("/messages")
    public String message(@RequestBody UserMessage prompt) {
        return chatBotServices.response(prompt);
    }
    

}
