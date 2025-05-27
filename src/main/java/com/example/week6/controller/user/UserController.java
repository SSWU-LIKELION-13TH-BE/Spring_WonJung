package com.example.week6.controller.user;

import com.example.week6.dto.user.request.UserLoginRequestDto;
import com.example.week6.dto.user.request.UserSignupRequestDto;
import com.example.week6.dto.user.response.UserLoginResponseDto;
import com.example.week6.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);

        System.out.println("DEBUG requestDto: " + requestDto);
        System.out.println("userId = " + requestDto.getUserId());
        System.out.println("password = " + requestDto.getPassword());
        return ResponseEntity.ok("회원가입 성공!");
    }

    // ✅ 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

}