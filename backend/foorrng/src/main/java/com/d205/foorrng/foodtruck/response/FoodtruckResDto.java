package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodtruckResDto {
    private final Long foodtruckId;
    private String announcement;
    private Long createdDay;
    private String picture;
    private String name;
    private String accountInfo;
    private String carNumber;
    private String phoneNumber;
    private List<String> category;

    // 생성
    public FoodtruckResDto(Foodtruck foodtruck, Long id, List<String> category){
        this.foodtruckId = id;
        this.announcement = foodtruck.getAnnouncement();
        this.createdDay = foodtruck.getCreatedDay();
        this.picture = foodtruck.getPicture();
        this.name = foodtruck.getName();
        this.accountInfo = foodtruck.getAccountInfo();
        this.carNumber = foodtruck.getCarNumber();
        this.phoneNumber = foodtruck.getPhoneNumber();
        this.category = category;
    }


}
