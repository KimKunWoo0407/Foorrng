package com.d205.foorrng.mark.controller;


import com.d205.foorrng.common.model.BaseResponseBody;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter @Setter
@RestController
@RequestMapping("/api/mark")
@RequiredArgsConstructor
public class MarkController {


    // 마커 생성
    public ResponseEntity<? extends BaseResponseBody> postMark(@RequestBody @Valid ) {

    }

    // 마커 수정


    // 마커 삭제


    // 마커 조회 ( 점주 운영 상태 )


    // 마커 조회 ( 소비자 이용할 푸드트럭 마커 )


    // 마커 상세 조회


}
