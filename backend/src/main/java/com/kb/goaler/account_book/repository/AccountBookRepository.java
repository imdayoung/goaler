package com.kb.goaler.account_book.repository;

import com.kb.goaler.account_book.entity.AccountBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountBookRepository extends JpaRepository<AccountBookEntity, Long> {

    Optional<AccountBookEntity> findByIdx(Long idx);
}
