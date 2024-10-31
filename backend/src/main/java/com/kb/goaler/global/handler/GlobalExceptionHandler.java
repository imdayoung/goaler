package com.kb.goaler.global.handler;

import com.kb.goaler.global.dto.ApiResponse;
import com.kb.goaler.global.dto.ErrorResponse;
import com.kb.goaler.global.error.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleApplicationException(ApplicationException e) {
        log.error("handleApplicationException : ", e);
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getCode());
        ApiResponse<ErrorResponse> response = ApiResponse.failure(errorResponse);
        return new ResponseEntity<>(response, e.getStatus());
    }
}
