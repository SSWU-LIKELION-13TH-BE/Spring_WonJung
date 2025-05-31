package com.example.week6.validation.apiPayload.code;

import com.example.week6.validation.apiPayload.dto.ErrorReasonDto;

public interface BaseErrorCode {

    ErrorReasonDto getReason();
    ErrorReasonDto getReasonHttpStatus();

}
