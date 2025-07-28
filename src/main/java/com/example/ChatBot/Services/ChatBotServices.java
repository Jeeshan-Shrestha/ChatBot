package com.example.ChatBot.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ChatBot.Models.UserMessage;

@Service
public class ChatBotServices {

    private String API = "LL2h2NULzaqCYnkbtfbBE6fdjgko5LrgA4UERVSb";
    private String url = "https://api.cohere.ai/generate";

    private StringBuilder messageHistory = new StringBuilder();

    @Autowired
    RestTemplate restTemplate;

    public String response(UserMessage prompt){

        messageHistory.append("User: ").append(prompt.getUserMessage()).append("\n");

        String finalPrompt = messageHistory.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API);

        Map<String,Object> body = new HashMap();
        body.put("prompt", finalPrompt);
        body.put("model", "command-r-plus");
        body.put("max_tokens",prompt.getToken());

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(body,headers);

        ResponseEntity<Map> response = restTemplate.exchange(url,HttpMethod.POST,request,Map.class);
        Map<String,Object>responseBody = response.getBody();
        String responseText = (String)responseBody.get("text");
        messageHistory.append("Bot: ").append(responseText);
        return responseText;

    }
    
}
