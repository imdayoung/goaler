package com.kb.goaler.account_history.controller;

import com.kb.goaler.account_history.dto.AccountBookHistoryResponse;
import com.kb.goaler.account_history.service.AccountHistoryService;
import com.kb.goaler.global.dto.ApiResponse;
import com.kb.goaler.member.dto.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-books")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;

    @GetMapping("/{accountBookIdx}/history")
    public ResponseEntity<ApiResponse<List<AccountBookHistoryResponse>>> getAllHistories(@PathVariable Long accountBookIdx) {
        List<AccountBookHistoryResponse> response = accountHistoryService.getAccountBookHistoryList();
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }
}
