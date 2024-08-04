package com.kb.goaler.account_history.service;

import com.kb.goaler.account_history.repository.AccountHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;
}
