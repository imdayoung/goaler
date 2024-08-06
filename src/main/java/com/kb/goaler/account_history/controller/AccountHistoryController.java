package com.kb.goaler.account_history.controller;

import com.kb.goaler.account_history.dto.AccountBookHistoryResponse;
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
@RequestMapping("/api/v1/account-books")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;

    @GetMapping("/{accountBookIdx}/history/expense")
    public ResponseEntity<ApiResponse<List<AccountHistoryChartResponse>>> getCurrentMonthExpenses(@PathVariable long accountBookIdx) {
        List<AccountHistoryChartResponse> responses = accountHistoryService.getCurrentMonthExpenses(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(responses));
    }

    @GetMapping("/{accountBookIdx}/history/income")
    public ResponseEntity<ApiResponse<List<AccountHistoryChartResponse>>> getCurrentMonthIncomes(@PathVariable long accountBookIdx) {
        List<AccountHistoryChartResponse> responses = accountHistoryService.getCurrentMonthIncomes(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(responses));
    }

    @GetMapping("/{accountBookIdx}/history")
    public ResponseEntity<ApiResponse<List<AccountBookHistoryResponse>>> getAllHistories(@PathVariable Long accountBookIdx) {
        List<AccountBookHistoryResponse> response = accountHistoryService.getAccountBookHistoryList(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    @GetMapping("/{accountBookIdx}/history/ai")
    public String getAccountHistoriesForAI(@PathVariable Long accountBookIdx) {
        return accountHistoryService.getAccountHistoriesForAI(accountBookIdx);
    }
}
