package com.d205.foorrng.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 푸드트럭
    FOODTRUCK_NOT_EXIST(HttpStatus.BAD_REQUEST, "F-001", "존재하지 않는 푸드트럭입니다."),

    // 유효성 검사
    NOT_VALID_REQUEST(HttpStatus.BAD_REQUEST, "V-001", "요청변수가 유효하지 않습니다.")

    ;


    // 상태, 에러 코드, 메시지
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    // 생성자
    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

}
