package com.d205.foorrng.operationInfo.service;


import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.mark.dto.MarkReqDto;
import com.d205.foorrng.mark.repository.MarkRepository;
import com.d205.foorrng.operationInfo.OperationInfo;
import com.d205.foorrng.operationInfo.dto.OperationInfoDto;
import com.d205.foorrng.operationInfo.repository.OperationInfoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Getter @Setter
@RequiredArgsConstructor
public class OperationInfoService {

    private final MarkRepository markRepository;
    private final OperationInfoRepository operationInfoRepository;

    public List<OperationInfo> createOperationInfo(Long markId, OperationInfoDto operationInfoDto) {

        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> new Exceptions(ErrorCode.MARK_NOT_EXIST));


        for (Map<String, Object> day : operationInfoDto.getOperationInfoList()) {

            OperationInfo operationInfo = OperationInfo
                    .builder()
                    .mark(mark)
                    .day(day.get("day").toString())
                    .startTime(Long.parseLong(day.get("startTime").toString()))
                    .endTime(Long.parseLong(day.get("startTime").toString()))
                    .build();

            operationInfoRepository.save(operationInfo);
        }
        List<OperationInfo> operationResponse = operationInfoRepository.findAllByMarkId(markId).get();

        return operationResponse;
    }

}
