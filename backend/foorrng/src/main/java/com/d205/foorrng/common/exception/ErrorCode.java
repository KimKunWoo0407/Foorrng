package com.d205.foorrng.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // NOT FOUND
    FOODTRUCK_NOT_FOUND(HttpStatus.BAD_REQUEST, "F-001", "존재하지 않는 푸드트럭입니다.")

    ;

    // BAD REQUEST


    // UNAUTHORIZED


    // 상태, 에러 코드, 메시지
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    // 생성자
    ErrorCode(HttpStatus httpStatus, String errorCode, String message){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
