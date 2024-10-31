package com.kb.goaler.account_history.entity;

import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.account_history.constant.Type;
import com.kb.goaler.category.entity.CategoryEntity;
import com.kb.goaler.member_account_book.entity.MemberAccountBookEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "account_history")
public class AccountHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String title;

    private long amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_idx")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "member_account_book_idx")
    private MemberAccountBookEntity memberAccountBook;
}
