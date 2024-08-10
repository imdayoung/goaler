package com.kb.goaler.gpt_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String prompt;
    private String accountBookIdx; // 이 필드는 선택 사항으로 설정합니다.
}