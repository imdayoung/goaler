package com.kb.goaler.account_history.dto;

import com.kb.goaler.account_history.entity.AccountHistoryEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountHistoryChartResponse {

    private long idx;
    private long amount;
    private long categoryIdx;
    private LocalDateTime createdAt;

    public static AccountHistoryChartResponse of(AccountHistoryEntity accountHistoryEntity) {
        return AccountHistoryChartResponse.builder()
                .idx(accountHistoryEntity.getIdx())
                .amount(accountHistoryEntity.getAmount())
                .categoryIdx(accountHistoryEntity.getCategory().getIdx())
                .createdAt(accountHistoryEntity.getCreatedAt())
                .build();
    }
}
