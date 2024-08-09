package com.kb.goaler.account_book.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountBookAIResponse {

    private String title;
    private String goal;
    private long balance;
    private Long totalSaving;
}
