package com.kb.goaler.account_history.service;

import com.kb.goaler.account_history.constant.Type;
import com.kb.goaler.account_history.dto.AccountBookHistoryResponse;
import com.kb.goaler.account_history.dto.AccountHistoryChartResponse;
import com.kb.goaler.account_history.entity.AccountHistoryEntity;
import com.kb.goaler.account_history.repository.AccountHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AccountHistoryService {

    private final AccountHistoryRepository accountHistoryRepository;

    public List<AccountHistoryChartResponse> getCurrentMonthExpenses(long accountBookIdx) {
        LocalDate now = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(now);

        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime startOfNextMonth = currentMonth.plusMonths(1).atDay(1).atStartOfDay();

        List<AccountHistoryEntity> accountHistoryEntityList = accountHistoryRepository.findExpensesForCurrentMonth(Type.EXPENSE, startOfMonth, startOfNextMonth, accountBookIdx);
        List<AccountHistoryChartResponse> responses = new ArrayList<>();

        for (AccountHistoryEntity accountHistoryEntity : accountHistoryEntityList) {
            responses.add(AccountHistoryChartResponse.of(accountHistoryEntity));
        }

        return responses;
    }

    public List<AccountHistoryChartResponse> getCurrentMonthIncomes(long accountBookIdx) {
        LocalDate now = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(now);

        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime startOfNextMonth = currentMonth.plusMonths(1).atDay(1).atStartOfDay();

        List<AccountHistoryEntity> accountHistoryEntityList = accountHistoryRepository.findExpensesForCurrentMonth(Type.INCOME, startOfMonth, startOfNextMonth, accountBookIdx);
        List<AccountHistoryChartResponse> responses = new ArrayList<>();

        for (AccountHistoryEntity accountHistoryEntity : accountHistoryEntityList) {
            responses.add(AccountHistoryChartResponse.of(accountHistoryEntity));
        }

        return responses;
    }

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
