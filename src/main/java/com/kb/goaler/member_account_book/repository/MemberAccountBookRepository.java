package com.kb.goaler.member_account_book.repository;


import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.member.entity.MemberEntity;
import com.kb.goaler.member_account_book.entity.MemberAccountBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberAccountBookRepository extends JpaRepository<MemberAccountBookEntity, Long> {

    List<MemberAccountBookEntity> findAllByAccountBookIdx(Long accountBookIdx);

    Optional<MemberAccountBookEntity> findByAccountBookAndMember(AccountBookEntity accountBook, MemberEntity member);

    List<MemberAccountBookEntity> findAllByMember(MemberEntity member);
}
