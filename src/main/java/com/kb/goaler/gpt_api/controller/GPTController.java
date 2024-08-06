package com.kb.goaler.gpt_api.controller;

import com.kb.goaler.gpt_api.dto.GPTRequest;
import com.kb.goaler.gpt_api.dto.GPTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/gpt")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")  // Vue.js가 실행되는 포트
public class GPTController {

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;


    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        GPTRequest request = new GPTRequest(model, prompt, 0.1, 150, 0, 0, -1);

        GPTResponse gptResponse = restTemplate.postForObject(
                apiUrl,
                request,
                GPTResponse.class
        );

        return gptResponse.getChoices().get(0).getMessage().getContent();
    }
}
