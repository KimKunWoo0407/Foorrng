package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.FoodtruckReport;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodtruckRepResDto {
    private Long foodtruckId;
    private String name;
    private String picture;
    private String carNumber;
    private String announcement;
    private String accountInfo;
    private String phoneNumber;
    private List<String> category;

    public FoodtruckRepResDto(FoodtruckReport foodtruck) {
        this.announcement = foodtruck.getAnnouncement();
        this.picture = foodtruck.getPicture();
        this.name = foodtruck.getName();
        this.accountInfo = foodtruck.getAccountInfo();
        this.carNumber = foodtruck.getCarNumber();
        this.phoneNumber = foodtruck.getPhoneNumber();
    }

    public static FoodtruckRepResDto fromEntity(FoodtruckReport foodtruck) {
        return new FoodtruckRepResDto(foodtruck);
    }
}
