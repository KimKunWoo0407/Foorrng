package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;


@Getter
public class FoodtruckCreateReqDto {
    private String announcement;
    @NotBlank
    private String name;
    private String accountInfo;
    @NotBlank
    private String carNumber;
    private String phoneNumber;
    private List<String> category;
}
