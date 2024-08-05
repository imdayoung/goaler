package com.kb.goaler.account_history.repository;

import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.account_history.entity.AccountHistoryEntity;
import com.kb.goaler.member.entity.MemberEntity;
import com.kb.goaler.member_account_book.entity.MemberAccountBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistoryEntity, Long> {

    List<AccountHistoryEntity> findAllByAccountBookIdx(Long accountBookIdx);
}
