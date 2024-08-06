package com.kb.goaler.account_book.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountBookInfoResponse {

    private Long idx;
    private String accountNumber;
    private String accountName;
    private String title;
    private Long balance;
    private String goal;
}
