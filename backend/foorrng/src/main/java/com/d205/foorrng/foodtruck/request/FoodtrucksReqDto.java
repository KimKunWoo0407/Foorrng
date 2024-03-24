package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FoodtrucksReqDto {
    @NotBlank
    private double latitude_left;
    @NotBlank
    private double longitude_left;
    @NotBlank
    private double latitude_right;
    @NotBlank
    private double longitude_right;
}
