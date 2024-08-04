package com.kb.goaler.account_book.service;

import com.kb.goaler.account_book.dto.AccountBookInfoResponse;
import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.account_book.repository.AccountBookRepository;
import com.kb.goaler.global.error.ApplicationError;
import com.kb.goaler.global.error.ApplicationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                .title(accountBookEntity.getTitle())
                .accountNumber(accountBookEntity.getAccountNumber())
                .balance(accountBookEntity.getBalance())
                .build();
    }
}
