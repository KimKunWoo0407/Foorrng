package com.d205.foorrng.mark.dto;

import com.d205.foorrng.operationInfo.OperationInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter

public class MarkReqDto {

//    private Long foodtruckId;

    private String address;

    private List<Map<String, Object>> days;

//    private Long startTime;
//
//    private Long endTime;

    private Double latitude;

    private Double longitude;


}
