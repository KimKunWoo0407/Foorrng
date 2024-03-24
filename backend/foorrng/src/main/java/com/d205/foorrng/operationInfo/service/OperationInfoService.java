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
        List<OperationInfo> operationResponse = operationInfoRepository.findAllByMarkId(markId)
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        return operationResponse;
    }

    public List<OperationInfo> searchOperationInfo(Long markId) {

        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> new Exceptions(ErrorCode.MARK_NOT_EXIST));

        List<OperationInfo> operationInfoList = operationInfoRepository.findAllByMarkId(mark.getId())
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        return operationInfoList;
    }


    public OperationInfo updateOperationInfo(Long operId, OperationInfoDto operationInfoDto) {

        OperationInfo operationInfo = operationInfoRepository.findById(operId)
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        operationInfo.update(operationInfoDto);

        operationInfoRepository.save(operationInfo);

        return operationInfo;
    }


    public void removeOperationInfo(Long operId) {

        OperationInfo operationInfo = operationInfoRepository.findById(operId)
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        operationInfoRepository.delete(operationInfo);
    }


}
