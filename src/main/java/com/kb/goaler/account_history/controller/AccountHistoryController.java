package com.kb.goaler.account_history.controller;

import com.kb.goaler.account_history.dto.AccountHistoryChartResponse;
import com.kb.goaler.account_history.service.AccountHistoryService;
import com.kb.goaler.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;

    @GetMapping("/account-books/{accountBookIdx}/history/expense")
    public ResponseEntity<ApiResponse<List<AccountHistoryChartResponse>>> getCurrentMonthExpenses(@PathVariable long accountBookIdx) {
        List<AccountHistoryChartResponse> responses = accountHistoryService.getCurrentMonthExpenses(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(responses));
    }

    @GetMapping("/account-books/{accountBookIdx}/history/income")
    public ResponseEntity<ApiResponse<List<AccountHistoryChartResponse>>> getCurrentMonthIncomes(@PathVariable long accountBookIdx) {
        List<AccountHistoryChartResponse> responses = accountHistoryService.getCurrentMonthIncomes(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(responses));
    }
}
