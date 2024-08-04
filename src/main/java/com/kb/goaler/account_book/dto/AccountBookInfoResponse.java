package com.kb.goaler.account_book.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountBookInfoResponse {

    private Long idx;
    private String title;
    private String accountNumber;
    private Long balance;
}
