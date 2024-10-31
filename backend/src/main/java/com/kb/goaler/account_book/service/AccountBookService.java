package com.kb.goaler.account_book.service;

import com.kb.goaler.account_book.dto.AccountBookAIResponse;
import com.kb.goaler.account_book.dto.AccountBookInfoResponse;
import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.account_book.repository.AccountBookRepository;
import com.kb.goaler.global.error.ApplicationError;
import com.kb.goaler.global.error.ApplicationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;

    public AccountBookInfoResponse getAccountBookInfo(Long accountBookIdx) {

        AccountBookEntity accountBookEntity = accountBookRepository.findByIdx(accountBookIdx)
                .orElseThrow(() -> new ApplicationException(ApplicationError.ACCOUNT_BOOK_NOT_FOUND));

        return AccountBookInfoResponse.builder()
                .idx(accountBookEntity.getIdx())
                .accountNumber(accountBookEntity.getAccountNumber())
                .accountName(accountBookEntity.getAccountName())
                .title(accountBookEntity.getTitle())
                .balance(accountBookEntity.getBalance())
                .goal(accountBookEntity.getGoal())
                .build();
    }

    public String getAccountBooksForAI(Long accountBookIdx) {

        AccountBookEntity accountBookEntity = accountBookRepository.findByIdx(accountBookIdx)
                .orElseThrow(() -> new ApplicationException(ApplicationError.ACCOUNT_BOOK_NOT_FOUND));

        AccountBookAIResponse response = AccountBookAIResponse.builder()
                .title(accountBookEntity.getTitle())
                .goal(accountBookEntity.getGoal())
                .balance(accountBookEntity.getBalance())
                .totalSaving(accountBookEntity.getTotalSaving())
                .build();

        return formatAccountBooksAsTable(response);
    }

    private String formatAccountBooksAsTable(AccountBookAIResponse response) {

        StringBuilder table = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        // Table header
        table.append("+-------------------------+-------------------------+----------------+--------------------+").append(lineSeparator);
        table.append("|          title          |          goal           |    balance     |     totalSaving    |").append(lineSeparator);
        table.append("+-------------------------+-------------------------+----------------+--------------------+").append(lineSeparator);

        // Table row
        table.append(String.format("| %-23s | %-23s | %-14d | %-14d |",
                response.getTitle(), response.getGoal(), response.getBalance(), response.getTotalSaving())).append(lineSeparator);

        // Table footer
        table.append("+-------------------------+-------------------------+----------------+--------------------+").append(lineSeparator);

        return table.toString();
    }
}
