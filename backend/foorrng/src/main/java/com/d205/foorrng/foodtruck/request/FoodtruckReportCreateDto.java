package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodtruckReportCreateDto {
    @NotEmpty
    private String name;
    private String picture;
    @NotEmpty
    private String carNumber;
    private String announcement;
    private String accountInfo;
    private String phoneNumber;
    @NotEmpty
    private List<String> category;
}
