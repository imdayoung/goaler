package com.kb.goaler.member_account_book.service;

import com.kb.goaler.account_book.entity.AccountBookEntity;
import com.kb.goaler.account_book.repository.AccountBookRepository;
import com.kb.goaler.global.error.ApplicationError;
import com.kb.goaler.global.error.ApplicationException;
import com.kb.goaler.member.dto.MemberInfoRequest;
import com.kb.goaler.member.dto.MemberInfoResponse;
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
public class MemberAccountBookService {

    private final MemberAccountBookRepository memberAccountBookRepository;
    private final MemberRepository memberRepository;
    private final AccountBookRepository accountBookRepository;

    public List<MemberInfoResponse> getMemberInfoList(Long accountBookIdx) {

        List<MemberAccountBookEntity> memberAccountBookEntityList = memberAccountBookRepository.findAllByAccountBookIdx(accountBookIdx);

        List<MemberInfoResponse> memberInfoResponseList = new ArrayList<>();

        for (MemberAccountBookEntity entity : memberAccountBookEntityList) {
            Optional<MemberEntity> memberEntity = memberRepository.findByIdx(entity.getMember().getIdx());

            if (memberEntity.isPresent()) {
                MemberEntity member = memberEntity.get();
                MemberInfoResponse memberInfoResponse = MemberInfoResponse.builder()
                        .idx(member.getIdx())
                        .name(member.getName())
                        .email(member.getEmail())
                        .age(member.getAge())
                        .image(member.getImage())
                        .build();
                memberInfoResponseList.add(memberInfoResponse);
            }
        }
        return memberInfoResponseList;
    }


    public void inviteMember(Long accountBookIdx, MemberInfoRequest request) {

        AccountBookEntity accountBook = accountBookRepository.findByIdx(accountBookIdx).orElseThrow(() -> new ApplicationException(ApplicationError.ACCOUNT_BOOK_NOT_FOUND));
        MemberEntity member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ApplicationException(ApplicationError.MEMBER_NOT_FOUND));

        if (memberAccountBookRepository.findByAccountBookAndMember(accountBook, member).isPresent()) {
            throw new ApplicationException(ApplicationError.MEMBER_ALREADY_INVITED);
        }
        MemberAccountBookEntity memberAccountBook = MemberAccountBookEntity.builder()
                .accountBook(accountBook)
                .member(member)
                .build();

        memberAccountBookRepository.save(memberAccountBook);
    }
}
