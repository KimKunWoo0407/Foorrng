package com.d205.foorrng.mark.controller;


import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.mark.dto.MarkDto;
import com.d205.foorrng.mark.dto.MarkReqDto;
import com.d205.foorrng.mark.service.MarkService;
import com.d205.foorrng.operationInfo.OperationInfo;
import com.d205.foorrng.operationInfo.dto.OperationInfoDto;
import com.d205.foorrng.operationInfo.service.OperationInfoService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@RestController
@RequestMapping("/api/mark")
@RequiredArgsConstructor
public class MarkController {


    private final MarkService markService;
    private final OperationInfoService operationInfoService;

    // 마커 생성
    @PostMapping("/{foodtruck-id}/regist")
    public ResponseEntity<? extends BaseResponseBody> postMark(@PathVariable("foodtruck-id") Long foodtruckId,
                                                               @RequestBody @Valid MarkReqDto markReqDto) {

        Map<String, Object> response = markService.createMark(foodtruckId, markReqDto.getMarkDto());
//        response.put("operationInfos", operationInfoService.createOperationInfo(Long.parseLong(response.get("markId").toString()), markReqDto));
        List<OperationInfo> operationInfoList = operationInfoService.createOperationInfo(Long.parseLong(response.get("markId").toString()), markReqDto.getOperationInfoDto());
        response.put("operationInfoList", operationInfoList);


        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, response));

    }

    // 마커 수정
    @PatchMapping("/{mark-id}")
    public ResponseEntity<? extends BaseResponseBody> patchMark(@PathVariable("mark-id") Long markId,
                                                                @RequestBody @Valid MarkDto markDto) {
        Mark mark = markService.updateMark(markId, markDto);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, mark));
    }


    // 마커 삭제
    @DeleteMapping("/{mark-id}")
    public ResponseEntity<? extends BaseResponseBody> patchMark(@PathVariable("mark-id") Long markId) {
        markService.removeMark(markId);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, "마커가 삭제되었습니다."));
    }



    // 마커 조회 ( 점주 운영 상태 )
    @GetMapping("/owner/list")
    public ResponseEntity<? extends BaseResponseBody> getOwnerMarkList() {

        List<Mark> markList = markService.searchOwnerMarkList();

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, markList));
    }


    // 마커 조회 ( 소비자 이용할 푸드트럭 마커 )


    // 마커 상세 조회


}
