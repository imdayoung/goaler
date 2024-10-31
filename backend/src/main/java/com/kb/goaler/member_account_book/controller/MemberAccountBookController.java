package com.kb.goaler.member_account_book.controller;

import com.kb.goaler.global.dto.ApiResponse;
import com.kb.goaler.member.dto.MemberInfoRequest;
import com.kb.goaler.member.dto.MemberInfoResponse;
import com.kb.goaler.member_account_book.service.MemberAccountBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-books")
public class MemberAccountBookController {

    private final MemberAccountBookService memberAccountBookService;

    @GetMapping("/{accountBookIdx}/users")
    public ResponseEntity<ApiResponse<List<MemberInfoResponse>>> getMembers(@PathVariable Long accountBookIdx) {
        List<MemberInfoResponse> responses = memberAccountBookService.getMemberInfoList(accountBookIdx);
        return ResponseEntity.ok().body(ApiResponse.success(responses));
    }

    @PostMapping("/{accountBookIdx}/users")
    public ResponseEntity<ApiResponse<Void>> inviteMembers(@PathVariable Long accountBookIdx,
                                                           @RequestBody MemberInfoRequest request) {

        memberAccountBookService.inviteMember(accountBookIdx, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
