package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;


// 제보 푸드트럭 등록 및 수정 시 사용
@Getter
public class FoodtruckReportCreateReqDto {
    @NotBlank
    private String name;
    private String picture;
    @NotBlank
    private String carNumber;
    private String announcement;
    private String accountInfo;
    private String phoneNumber;
    private List<String> category;
}
