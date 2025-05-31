package com.example.week6.validation.apiPayload.exception;

import com.example.week6.validation.apiPayload.code.BaseErrorCode;
import com.example.week6.validation.apiPayload.dto.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
    public ErrorReasonDto getErrorReason() {
        return this.code.getReason();
    }
    public ErrorReasonDto getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }

}
