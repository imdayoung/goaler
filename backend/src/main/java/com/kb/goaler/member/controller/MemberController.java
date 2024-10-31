package com.kb.goaler.member.controller;

import com.kb.goaler.global.dto.ApiResponse;
import com.kb.goaler.member.dto.MemberAccountBookListResponse;
import com.kb.goaler.member.dto.MemberInfoRequest;
import com.kb.goaler.member.dto.MemberInfoResponse;
import com.kb.goaler.member.dto.MemberRegisterRequest;
import com.kb.goaler.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<MemberInfoResponse>> testGet(@RequestBody MemberInfoRequest memberInfoRequest) {
        MemberInfoResponse response = memberService.findMember(memberInfoRequest);
        return ResponseEntity.ok().body(ApiResponse.success(response));

    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<String>> memberRegister(@RequestBody MemberRegisterRequest memberRegisterRequest) {
        memberService.register(memberRegisterRequest);
        return ResponseEntity.ok().body(ApiResponse.success("Success"));
    }

    @GetMapping("/{memberIdx}/account-books")
    public ResponseEntity<ApiResponse<List<MemberAccountBookListResponse>>> getMemberAccountBooks(@PathVariable Long memberIdx) {
        List<MemberAccountBookListResponse> response = memberService.getAccountBookList(memberIdx);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }
}
