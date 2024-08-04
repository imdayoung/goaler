package com.kb.goaler.account_history.controller;

import com.kb.goaler.account_history.service.AccountHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;
}
