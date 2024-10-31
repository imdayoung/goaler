package com.kb.goaler.gpt_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {//여기서 content는 요청을 보낼땐 prompt가 되고 받을땐 gpt가 응답한 내용이 된다

    private String role;
    private String content;

}