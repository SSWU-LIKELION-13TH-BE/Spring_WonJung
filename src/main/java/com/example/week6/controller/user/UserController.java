package com.example.week6.controller.user;

import com.example.week6.dto.user.request.UserLoginRequestDto;
import com.example.week6.dto.user.request.UserPasswordChangeRequestDto;
import com.example.week6.dto.user.request.UserSignupRequestDto;
import com.example.week6.dto.user.response.UserInfoResponseDto;
import com.example.week6.dto.user.response.UserLoginResponseDto;
import com.example.week6.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // ✅ 사용자 정보 조회
    @GetMapping("/me")
    public ResponseEntity<?> getInfo(Authentication authentication) {

        String userId = authentication.getName();
        UserInfoResponseDto userInfo = userService.getUserInfo(userId);
        return ResponseEntity.ok(userInfo);

    }

    // ✅ 비밀번호 변경
    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody UserPasswordChangeRequestDto requestDto) {

        String userId = authentication.getName();
        userService.changePassword(userId, requestDto);

        return ResponseEntity.status(200).body(Map.of("status", "success", "message", "비밀번호가 성공적으로 변경되었습니다."));
    }

}