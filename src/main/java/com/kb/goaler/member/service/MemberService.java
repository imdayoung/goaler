package com.kb.goaler.member.service;

import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.account_book.repository.AccountBookRepository;
import com.kb.goaler.global.error.ApplicationError;
import com.kb.goaler.global.error.ApplicationException;
import com.kb.goaler.member.dto.MemberAccountBookListResponse;
import com.kb.goaler.member.dto.MemberInfoRequest;
import com.kb.goaler.member.dto.MemberInfoResponse;
import com.kb.goaler.member.dto.MemberRegisterRequest;
import com.kb.goaler.member.entity.MemberEntity;
import com.kb.goaler.member.repository.MemberRepository;
import com.kb.goaler.member_account_book.entity.MemberAccountBookEntity;
import com.kb.goaler.member_account_book.repository.MemberAccountBookRepository;
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
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberAccountBookRepository memberAccountBookRepository;
    private final AccountBookRepository accountBookRepository;

    public MemberInfoResponse findMember(MemberInfoRequest request) {
        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(request.getEmail());

        MemberEntity member = memberEntity.orElseThrow(() -> new ApplicationException(ApplicationError.MEMBER_NOT_FOUND));
        return MemberInfoResponse.builder()
                .idx(member.getIdx())
                .email(member.getEmail())
                .name(member.getName())
                .age(member.getAge())
                .image(member.getImage())
                .build();
    }

    public void register(MemberRegisterRequest request) {

        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(request.getEmail());
        if (memberEntity.isPresent()) {
            throw new ApplicationException(ApplicationError.EMAIL_DUPLICATED);
        }

        MemberEntity member = MemberEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .age(request.getAge())
                .image(request.getImage())
                .build();

        memberRepository.save(member);
    }

    public List<MemberAccountBookListResponse> getAccountBookList(Long memberIdx) {

        List<MemberAccountBookListResponse> accountBookListResponse = new ArrayList<>();

        Optional<MemberEntity> memberEntity = memberRepository.findByIdx(memberIdx);
        MemberEntity member = memberEntity.orElseThrow(() -> new ApplicationException(ApplicationError.MEMBER_NOT_FOUND));

        List<MemberAccountBookEntity> accountBookEntityList = memberAccountBookRepository.findAllByMember(member);

        for (MemberAccountBookEntity memberAccountBookEntity : accountBookEntityList) {
            Optional<AccountBookEntity> accountBookEntity = accountBookRepository.findByIdx(memberAccountBookEntity.getAccountBook().getIdx());
            AccountBookEntity accountBook = accountBookEntity.orElseThrow(() -> new ApplicationException(ApplicationError.ACCOUNT_BOOK_NOT_FOUND));

            MemberAccountBookListResponse memberAccountBook = MemberAccountBookListResponse.builder()
                    .idx(accountBook.getIdx())
                    .title(accountBook.getTitle())
                    .account_name(accountBook.getAccountName())
                    .build();

            accountBookListResponse.add(memberAccountBook);
        }

        return accountBookListResponse;
    }
}
