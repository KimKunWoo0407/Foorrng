package com.d205.foorrng.common.exception;

import lombok.Getter;

@Getter
public class Exceptions extends RuntimeException{

    // 에러 코드
    private ErrorCode errorCode;

    // 생성자
    public Exceptions(ErrorCode errorCode){

        // 인자가 아니면
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
