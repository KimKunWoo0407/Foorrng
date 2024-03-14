package com.d205.foorrng.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TmpResponseDto<T> {

    private boolean result; // 성공 여부
    private String msg; // 결과 메시지
    private T data; // 결과 데이터
}
