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
            TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 예외입니다."),
            USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER4001", "이미 존재하는 아이디입니다."),
            PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "USER4002", "현재 비밀번호가 일치하지 않습니다."),
            PASSWORD_CONFIRM_MISMATCH(HttpStatus.BAD_REQUEST, "USER4003", "새 비밀번호와 확인 바밀번호가 일치하지 않습니다.");

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
