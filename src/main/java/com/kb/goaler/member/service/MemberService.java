package com.kb.goaler.member.service;

import com.kb.goaler.global.error.ApplicationError;
import com.kb.goaler.global.error.ApplicationException;
import com.kb.goaler.member.dto.MemberInfoRequest;
import com.kb.goaler.member.dto.MemberInfoResponse;
import com.kb.goaler.member.dto.MemberRegisterRequest;
import com.kb.goaler.member.entity.MemberEntity;
import com.kb.goaler.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

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
}
