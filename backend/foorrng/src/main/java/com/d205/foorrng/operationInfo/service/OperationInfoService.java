package com.d205.foorrng.operationInfo.service;


import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.mark.dto.MarkReqDto;
import com.d205.foorrng.mark.repository.MarkRepository;
import com.d205.foorrng.operationInfo.OperationInfo;
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

    public List<OperationInfo> createOperationInfo(Long markId, MarkReqDto markReqDto) {

        Mark mark = markRepository.findById(markId).get();


        for (Map<String, Object> day : markReqDto.getDays()) {

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
