package com.kb.goaler.member_account_book.repository;


import com.kb.goaler.member_account_book.entity.MemberAccountBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberAccountBookRepository extends JpaRepository<MemberAccountBookEntity, Long> {

    List<MemberAccountBookEntity> findAllByAccountBookIdx(Long accountBookIdx);
}
