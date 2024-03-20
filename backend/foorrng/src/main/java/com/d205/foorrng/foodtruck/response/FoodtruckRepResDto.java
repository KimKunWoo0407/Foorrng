package com.d205.foorrng.foodtruck.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodtruckRepResDto {
    private String name;
    private String picture;
    private String carNumber;
    private String announcement;
    private String accountInfo;
    private String phoneNumber;
    private List<String> category;
}
