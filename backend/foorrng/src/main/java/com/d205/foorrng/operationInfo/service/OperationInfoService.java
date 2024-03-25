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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Getter @Setter
@RequiredArgsConstructor
public class OperationInfoService {

    private final MarkRepository markRepository;
    private final OperationInfoRepository operationInfoRepository;

//    @Transactional
    public List<OperationInfo> createOperationInfo(Long markId, OperationInfoDto operationInfoDto) {

        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> new Exceptions(ErrorCode.MARK_NOT_EXIST));

        Set<String> allDays = operationInfoRepository.findAllDaysByFoodTruckId(mark.getFoodtrucks().getId());
//        List<Mark> markList = markRepository.findAllByFoodtrucksId(mark.getFoodtrucks().getId()).get();
//
//        Set<String> operationInfoList = new HashSet<>();
//
//        for (Mark mk : markList) {
//            List<OperationInfo> opList = operationInfoRepository.findAllByMarkId(mk.getId()).get();
//            for (OperationInfo op : opList) {
//                operationInfoList.add(op.getDay());
//            }
//        }


        for (Map<String, Object> day : operationInfoDto.getOperationInfoList()) {
            if (allDays.contains(day.get("day"))) {
                System.out.println("1");
                if (mark.getOperationInfoList() == null || mark.getOperationInfoList().isEmpty()) {
                    System.out.println(markId);
                    System.out.println(mark.getId());
                    System.out.println("2");
                    markRepository.delete(mark);
                    System.out.println("3");
                };
                throw new Exceptions(ErrorCode.DAY_OCCUPIED);
            }
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

    @Transactional
    public List<OperationInfo> searchOperationInfo(Long markId) {

        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> new Exceptions(ErrorCode.MARK_NOT_EXIST));

        List<OperationInfo> operationInfoList = operationInfoRepository.findAllByMarkId(mark.getId())
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        return operationInfoList;
    }


    @Transactional
    public OperationInfo updateOperationInfo(Long operId, OperationInfoDto operationInfoDto) {

        OperationInfo operationInfo = operationInfoRepository.findById(operId)
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        operationInfo.update(operationInfoDto);

        operationInfoRepository.save(operationInfo);

        return operationInfo;
    }


    @Transactional
    public void removeOperationInfo(Long operId) {

        OperationInfo operationInfo = operationInfoRepository.findById(operId)
                .orElseThrow(() -> new Exceptions(ErrorCode.OPERATION_NOT_EXIST));

        operationInfoRepository.delete(operationInfo);
    }


}
