package com.kb.goaler.account_history.dto;

import com.kb.goaler.account_history.constant.Type;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountHistoryAIResponse {

    private String title;
    private Long amount;
    private Type type;
    private String category_name;
    private String member_name;
    private String created_at;
}
