package com.kb.goaler.account_history.repository;

import com.kb.goaler.account_history.entity.AccountHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistoryEntity, Long> {
}
