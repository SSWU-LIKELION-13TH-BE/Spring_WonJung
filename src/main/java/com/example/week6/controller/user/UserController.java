package com.example.week6.controller.user;

import com.example.week6.dto.user.request.UserLoginRequestDto;
import com.example.week6.dto.user.request.UserPasswordChangeRequestDto;
import com.example.week6.dto.user.request.UserSignupRequestDto;
import com.example.week6.dto.user.response.UserInfoResponseDto;
import com.example.week6.dto.user.response.UserLoginResponseDto;
import com.example.week6.service.user.UserService;
import com.example.week6.validation.apiPayload.code.SuccessStatus;
import com.example.week6.validation.apiPayload.dto.ApiResponse;
import com.example.week6.validation.test.dto.TestResponse;
import jakarta.validation.Valid;
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

    // 회원가입
    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
//        userService.signup(requestDto);
//        return ResponseEntity.ok("회원가입 성공!");
//    }

    // 응답 통일 + 예외 처리, 아이디 중복 검사 추가
    public ApiResponse<?> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ApiResponse.of(SuccessStatus._OK, new TestResponse("회원가입이 왼료되었습니다."));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    // 사용자 정보 조회
    @GetMapping("/me")
    public ResponseEntity<?> getInfo(Authentication authentication) {

        String userId = authentication.getName();
        UserInfoResponseDto userInfo = userService.getUserInfo(userId);
        return ResponseEntity.ok(userInfo);

    }

    // 비밀번호 변경
    @PatchMapping("/password")
//    public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody UserPasswordChangeRequestDto requestDto) {
//
//        String userId = authentication.getName();
//        userService.changePassword(userId, requestDto);
//
//        return ResponseEntity.status(200).body(Map.of("status", "success", "message", "비밀번호가 성공적으로 변경되었습니다."));
//    }

    // 응답 통일 + 예외 처리
    public ApiResponse<?> changePassword(Authentication authentication, @Valid @RequestBody UserPasswordChangeRequestDto requestDto) {

        String userId = authentication.getName();
        userService.changePassword(userId, requestDto);

        return ApiResponse.of(SuccessStatus._OK, new TestResponse("비밀번호 변경이 완료되었습니다."));
    }
}