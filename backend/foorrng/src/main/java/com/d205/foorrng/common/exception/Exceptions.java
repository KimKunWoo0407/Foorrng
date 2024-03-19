package com.d205.foorrng.common.exception;

import lombok.Getter;

@Getter
public class Exceptions extends RuntimeException{
    private ErrorCode errorCode;

    public Exceptions(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
