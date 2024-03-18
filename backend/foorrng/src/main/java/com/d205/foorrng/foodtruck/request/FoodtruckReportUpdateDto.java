package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class FoodtruckReportUpdateDto {
    @NotEmpty
    private String name;
    private String picture;
    @NotEmpty
    private String carNumber;
    private String announcement;
    private String accountInfo;
    private String phoneNumber;
}
