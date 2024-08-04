package com.kb.goaler.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationError {

    // 400
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "MEMBER_002", "이미 존재하는 이메일입니다."),
    MEMBER_ALREADY_INVITED(HttpStatus.BAD_REQUEST, "MEMBER_ACCOUNT_BOOK_001", "사용자가 이미 가계부에 초대되었습니다."),


    // 404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_001", "사용자를 찾을 수 없습니다."),
    ACCOUNT_BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT_BOOK_001", "가계부를 찾을 수 없습니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_001", "서버 내부 에러가 발생하였습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
