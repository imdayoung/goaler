package com.kb.goaler.gpt_api.controller;

import com.kb.goaler.gpt_api.dto.GPTRequest;
import com.kb.goaler.gpt_api.dto.GPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/gpt")
@RequiredArgsConstructor
public class GPTController {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;


    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){

        GPTRequest request = new GPTRequest(model,prompt,0,500,1,0,-1);

        GPTResponse gptResponse = restTemplate.postForObject(
                apiUrl
                , request
                , GPTResponse.class
        );

        return gptResponse.getChoices().get(0).getMessage().getContent();


    }
}