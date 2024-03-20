package com.d205.foorrng.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 회원
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "U-001", "존재하지 않는 회원입니다."),
    EMAIL_EXIST(HttpStatus.BAD_REQUEST, "U-002", "이미 가입된 회원입니다."),
    CATEGORY_NOT_EXIST(HttpStatus.BAD_REQUEST, "U-003", "존재하지 않는 항목입니다."),

    // 음식
    FOODLIST_NOT_EXIST(HttpStatus.BAD_REQUEST, "F-001", "선호음식 리스트가 존재하지 않습니다."),

    // 토큰
    UNEXPECTED_TOKEN(HttpStatus.BAD_REQUEST, "T-001", "토큰이 만료되었습니다."),
    TOKEN_NOT_EXIST(HttpStatus.BAD_REQUEST, "T-002", "토큰이 존재하지 않습니다."),

    // 푸드트럭
    FOODTRUCK_NOT_FOUND(HttpStatus.BAD_REQUEST, "F-001", "존재하지 않는 푸드트럭입니다."),
    // Validation
    NOT_VALID_REQUEST(HttpStatus.BAD_REQUEST, "I-001", "요청변수가 유효하지 않습니다.");

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
