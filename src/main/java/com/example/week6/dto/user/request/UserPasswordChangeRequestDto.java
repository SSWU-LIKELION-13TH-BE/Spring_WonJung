package com.example.week6.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserPasswordChangeRequestDto {

    private String currentPassword;

    @Size(min = 8, message = "비밀번호는 8자리 이상을 입력해 주세요.") @NotBlank(message = "새 비밀번호를 입력해 주세요.")
    private String newPassword;

    @Size(min = 8, message = "비밀번호는 8자리 이상을 입력해 주세요.") @NotBlank(message = "비밀번호를 다시 한 번 입력해 주세요.")
    private String confirmPassword;

}