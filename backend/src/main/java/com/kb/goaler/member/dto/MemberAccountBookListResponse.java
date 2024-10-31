package com.kb.goaler.member.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAccountBookListResponse {

    private Long idx;
    private String title;
    private String account_name;
}
