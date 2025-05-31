package com.example.week6.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

// 회원가입 요청 DTO

@Getter
@Builder
public class UserSignupRequestDto {

    @NotBlank(message = "이름을 입력해 주세요.") @Size(min = 2)
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요.") @Size(min = 8, message = "비밀번호는 8자리 이상을 입력해 주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    private String profileImage;

}