package com.example.ChatBot.Models;

import lombok.Data;

@Data
public class UserMessage {

    private String userMessage;

    private String model;

    private Integer token;
    
}
