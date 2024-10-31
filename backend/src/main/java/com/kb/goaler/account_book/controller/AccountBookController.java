package com.kb.goaler.account_book.controller;

import com.kb.goaler.account_book.dto.AccountBookInfoResponse;
import com.kb.goaler.account_book.service.AccountBookService;
import com.kb.goaler.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-books")
public class AccountBookController {

    private final AccountBookService accountBookService;

    @GetMapping("/{accountBookIdx}")
    public ResponseEntity<ApiResponse<AccountBookInfoResponse>> getAccountBook(@PathVariable Long accountBookIdx) {

        AccountBookInfoResponse response = accountBookService.getAccountBookInfo(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    @GetMapping("/{accountBookIdx}/ai")
    public String getAccountBooksForAIAi(@PathVariable Long accountBookIdx) {
        return accountBookService.getAccountBooksForAI(accountBookIdx);
    }
}
