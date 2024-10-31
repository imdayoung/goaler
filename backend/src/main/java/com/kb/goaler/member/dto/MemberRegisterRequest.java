package com.kb.goaler.member.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRegisterRequest {

    private String email;
    private String name;
    private String password;
    private int age;
    private String image;
}
