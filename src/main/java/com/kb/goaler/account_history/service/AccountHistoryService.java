package com.kb.goaler.account_history.service;

import com.kb.goaler.account_history.dto.AccountBookHistoryResponse;
import com.kb.goaler.account_history.entity.AccountHistoryEntity;
import com.kb.goaler.account_history.repository.AccountHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;

    public List<AccountBookHistoryResponse> getAccountBookHistoryList(Long accountBookIdx) {
        List<AccountBookHistoryResponse> list = new ArrayList<>();

        List<AccountHistoryEntity> accountHistoryEntityList = accountHistoryRepository.findAllByAccountBookIdx(accountBookIdx);

        for (AccountHistoryEntity accountHistoryEntity : accountHistoryEntityList) {
            AccountBookHistoryResponse history = AccountBookHistoryResponse.builder()
                    .idx(accountHistoryEntity.getIdx())
                    .title(accountHistoryEntity.getTitle())
                    .amount(accountHistoryEntity.getAmount())
                    .type(accountHistoryEntity.getType())
                    .category_name(accountHistoryEntity.getCategory().getName())
                    .created_at(accountHistoryEntity.getCreatedAt().toString())
                    .build();

            list.add(history);
        }

        return list;
    }
}
