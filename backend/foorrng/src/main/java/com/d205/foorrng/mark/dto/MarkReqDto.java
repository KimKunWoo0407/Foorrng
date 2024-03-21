package com.d205.foorrng.mark.dto;

import com.d205.foorrng.operationInfo.OperationInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter

public class MarkReqDto {

    private String address;

    private List<String> days;

    private Long startTime;

    private Long endTime;


}
