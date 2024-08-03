package com.kb.goaler.member_account_book.service;

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
}
