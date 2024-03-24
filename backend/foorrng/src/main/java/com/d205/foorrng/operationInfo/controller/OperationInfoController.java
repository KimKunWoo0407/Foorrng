package com.d205.foorrng.operationInfo.controller;


import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.operationInfo.OperationInfo;
import com.d205.foorrng.operationInfo.dto.OperationInfoDto;
import com.d205.foorrng.operationInfo.service.OperationInfoService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@RestController
@RequestMapping("/api/oper")
@RequiredArgsConstructor
public class OperationInfoController {

    private final OperationInfoService operationInfoService;

    @PostMapping("/{mark-id}/regist")
    public ResponseEntity<? extends BaseResponseBody> postOperationInfo(@PathVariable("mark-id") Long markId,
                                                                        @RequestBody @Valid OperationInfoDto operationInfoDto) {
        System.out.println(operationInfoDto.getOperationInfoList());
        Map<String, Object> response = new HashMap<>();
        List<OperationInfo> operationInfoList = operationInfoService.createOperationInfo(markId, operationInfoDto);

        response.put("markId", markId);
        response.put("operationInfoList", operationInfoList);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, response));
    }
}
