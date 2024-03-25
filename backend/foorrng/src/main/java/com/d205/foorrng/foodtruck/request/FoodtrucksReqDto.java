package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FoodtrucksReqDto {
    @NotBlank
    private double latitudeLeft;
    @NotBlank
    private double longitudeLeft;
    @NotBlank
    private double latitudeRight;
    @NotBlank
    private double longitudeRight;
}
