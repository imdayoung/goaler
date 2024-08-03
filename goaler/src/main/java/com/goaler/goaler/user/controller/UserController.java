package com.goaler.goaler.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class UserController {

    @GetMapping("/test1")
    public String test() {
        return "jwtTest 요청 성공";
    }
}
