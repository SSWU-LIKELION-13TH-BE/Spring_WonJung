package com.example.week6.validation.apiPayload.code;

import com.example.week6.validation.apiPayload.dto.ReasonDto;

public interface BaseCode {

    ReasonDto getReason(); // 메시지 및 코드 반환
    ReasonDto getReasonHttpStatus(); // 메시지 + HTTP 상태 포함 반환

}
