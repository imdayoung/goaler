package com.kb.goaler.account_history.repository;

import com.kb.goaler.account_history.constant.Type;
import com.kb.goaler.account_history.entity.AccountHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistoryEntity, Long> {

    @Query("SELECT a FROM AccountHistoryEntity a " +
            "JOIN a.memberAccountBook mab " +
            "JOIN mab.accountBook ab " +
            "WHERE a.type = :type " +
            "AND a.createdAt BETWEEN :startOfMonth AND :endOfMonth " +
            "AND ab.idx = :accountBookIdx")
    List<AccountHistoryEntity> findExpensesForCurrentMonth(@Param("type") Type type,
                                                           @Param("startOfMonth") LocalDateTime startOfMonth,
                                                           @Param("endOfMonth") LocalDateTime endOfMonth,
                                                           @Param("accountBookIdx") Long accountBookIdx);

    @Query("SELECT ah FROM AccountHistoryEntity ah JOIN ah.memberAccountBook mab JOIN mab.accountBook ab " +
            "WHERE ab.idx = :accountBookIdx ORDER BY ah.createdAt DESC")
    List<AccountHistoryEntity> findAllByAccountBookIdx(@Param("accountBookIdx") Long accountBookIdx);
}
