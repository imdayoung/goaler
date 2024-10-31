package com.kb.goaler.global.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ErrorResponse error;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static ApiResponse<ErrorResponse> failure(ErrorResponse errorResponse) {
        return ApiResponse.<ErrorResponse>builder()
                .success(false)
                .error(errorResponse)
                .build();
    }
}
