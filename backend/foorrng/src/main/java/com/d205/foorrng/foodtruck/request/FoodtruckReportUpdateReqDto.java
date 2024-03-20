package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import java.util.List;

@Getter
public class FoodtruckReportUpdateReqDto {
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
