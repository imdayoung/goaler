package com.kb.goaler.member_account_book.entity;

import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_account_book")
public class MemberAccountBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @JoinColumn(name = "member_idx")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    @JoinColumn(name = "account_book_idx")
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountBookEntity accountBook;
}
