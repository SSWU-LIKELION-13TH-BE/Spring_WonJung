package com.example.week6.validation.apiPayload.code;

import com.example.week6.validation.apiPayload.dto.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400",
            "잘못된 요청입니다."),
            TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 예외입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder().message(message).code(code).isSuccess(false).build();
    }
    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder().message(message).code(code).isSuccess(false).httpStatus(httpStatus).build();
    }
}
