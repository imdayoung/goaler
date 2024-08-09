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
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/getAnalysis/{accountBookIdx}")
    public String getAnalysis(@PathVariable String accountBookIdx) {


        try {
            // 1. @GetMapping("/{accountBookIdx}/history/ai")에서 데이터 가져오기
            String historyUrl = "http://localhost:8080/api/v1/account-books/" + accountBookIdx + "/history/ai";
            String historyResponse = restTemplate.getForObject(historyUrl, String.class);

            // 2. GPT에게 보낼 프롬프트 생성
            String prompt = " 이 멤버들의 가계부를 보고 소비경향을 분석해줘.누가 가장 많은 소비를 하는지,고정지출은 어떤 것이 있는지.멤버별 어떤 카테고리에 지출을 많이하는지를 포함해서 5문장의 줄글로 말해줘."
                    + historyResponse;


            // 3. GPT API 호출
            GPTRequest gptRequest = new GPTRequest(model, prompt, 0.1, 200, 0, 0, -1);
            GPTResponse gptResponse = restTemplate.postForObject(apiUrl, gptRequest, GPTResponse.class);

            // 4. GPT의 응답 반환
            return gptResponse.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            e.printStackTrace();  // 예외를 콘솔에 출력
            return "An error occurred";  // 에러 메시지 반환
        }
    }




}
