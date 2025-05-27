package com.example.week6.dto.user.request;

import lombok.Getter;

@Getter
public class UserPasswordChangeRequestDto {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

}
