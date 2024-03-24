package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
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

    public FoodtruckRepResDto(Foodtruck foodtruck) {
        this.announcement = foodtruck.getAnnouncement();
        this.picture = foodtruck.getPicture();
        this.name = foodtruck.getName();
        this.accountInfo = foodtruck.getAccountInfo();
        this.carNumber = foodtruck.getCarNumber();
        this.phoneNumber = foodtruck.getPhoneNumber();
    }

    public static FoodtruckRepResDto fromEntity(Foodtruck foodtruck) {
        return new FoodtruckRepResDto(foodtruck);
    }
}
