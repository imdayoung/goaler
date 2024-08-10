package com.kb.goaler.gpt_api.controller;

import com.kb.goaler.gpt_api.dto.ChatRequest;
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
    public String chat(@RequestBody ChatRequest chatRequest) {
        String prompt = chatRequest.getPrompt();
        String accountBookIdx = chatRequest.getAccountBookIdx();

        try {
            String historyUrl;
            String gptPrompt;

            // 사용자 입력에 '저금'이 포함된 경우
            if (prompt.contains("저금")) {
                if (accountBookIdx != null) {
                    // DB에서 데이터를 가져옵니다.
                    historyUrl = "http://localhost:8080/api/v1/account-books/" + accountBookIdx + "/ai";
                    String dbData = restTemplate.getForObject(historyUrl, String.class);

                    // GPT API 요청 프롬프트를 구성합니다.
                    gptPrompt = prompt + "이 테이블을 참고해. 테이블에서 title 이 '울 가족 여행'인 total_saving 컬럼이 우리가 한달동안 저금한 금액이야" + dbData + " 단 , 물어본거에만 간략하게 한문장으로 대답해.다른말 붙이지말고. 그리고 밝고 활기찬 톤의 존댓말로 해";
                } else {
                    gptPrompt = prompt;
                }
            } else { //그 외 질문
                if (accountBookIdx != null) {
                    // DB에서 데이터를 가져옵니다.
                    historyUrl = "http://localhost:8080/api/v1/account-books/" + accountBookIdx + "/history/ai";
                    String dbData = restTemplate.getForObject(historyUrl, String.class);

                    // GPT API 요청 프롬프트를능 구성합니다.
                    gptPrompt = "내 이름은 김국민이고 엄마 이름은 김사랑, 아빠 이름은 김은행이야." + prompt + " 다음 테이블을 보고 말해줘. "+ dbData + " 단 , 물어본거에만 간략하게 한문장으로 대답해.다른말 붙이지말고. 그리고 밝고 활기찬 톤의 존댓말로 해";
                } else {
                    //accountBookIdx null 일때,
                    gptPrompt = "accountBookIdx 가 null임." ;
                }
            }

            // gptPrompt를 콘솔에 출력하여 확인합니다.
            System.out.println("Generated GPT Prompt: " + gptPrompt);

            // GPT API 요청을 생성합니다.
            GPTRequest request = new GPTRequest(model, gptPrompt, 0.1, 100, 0, 0, -1);
            GPTResponse gptResponse = restTemplate.postForObject(apiUrl, request, GPTResponse.class);

            // GPT의 응답을 반환합니다.
            return gptResponse.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            e.printStackTrace();  // 예외를 콘솔에 출력
            return "An error occurred";  // 에러 메시지 반환
        }
    }


    //소비리포트-------gpt때매 잠시 꺼놈
    @GetMapping("/getAnalysis/{accountBookIdx}")
    public String getAnalysis(@PathVariable String accountBookIdx) {

        try {
            // 1. @GetMapping("/{accountBookIdx}/history/ai")에서 데이터 가져오기
            String historyUrl = "http://localhost:8080/api/v1/account-books/" + accountBookIdx + "/history/ai";
            String historyResponse = restTemplate.getForObject(historyUrl, String.class);

            // 2. GPT에게 보낼 프롬프트 생성
            String prompt = historyResponse + " 이 멤버들의 가계부를 보고 소비경향을 분석해줘. 밝고 활기찬 톤의 존댓말로 부탁해. 누가 가장 많은 소비를 하는지, 고정 수입과 지출은 어떤 것이 있는지, 멤버별 어떤 카테고리에 지출을 많이하는지, 가장 많이 소비한 카테고리를 줄이라는 충고를 포함해서 5문장의 줄글로 말해줘. " ;


            // 3. GPT API 호출
            GPTRequest gptRequest = new GPTRequest(model, prompt, 0.1, 250, 0, 0, -1);
            GPTResponse gptResponse = restTemplate.postForObject(apiUrl, gptRequest, GPTResponse.class);

            // 4. GPT의 응답 반환
            return gptResponse.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            e.printStackTrace();  // 예외를 콘솔에 출력
            return "An error occurred";  // 에러 메시지 반환
        }
    }




}
