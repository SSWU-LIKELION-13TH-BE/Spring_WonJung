package com.example.week6.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponseDto {

    private String userId;
    private String name;
    private String profileImage;

}