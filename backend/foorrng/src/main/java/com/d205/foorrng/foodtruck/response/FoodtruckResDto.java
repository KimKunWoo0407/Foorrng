package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import lombok.Getter;

import java.util.List;


@Getter
public class FoodtruckResDto {
    private final Long foodtruckId;                // 푸드트럭 식별 ID

    private String announcement;            // 공지사항

    private Long createdDay;               // 등록일

    private String picture;                 // 푸듣트럭 차 사진

    private String name;                    // 가게 이름

    private String accountInfo;            // 계봐 번호

    private String carNumber;              // 차량 번호

    private String phoneNumber;            // 연락처
    private List<String> category;

//    private List<FoodtrucksResDto> foodtrucksResDtoList; // 추가 하는부분..

    // 생성
    public FoodtruckResDto(Foodtruck foodtruck, Long id, Long createdDay, List<String> category){
        this.foodtruckId = id;
        this.announcement = foodtruck.getAnnouncement();
        this.createdDay = createdDay;
        this.picture = foodtruck.getPicture();
        this.name = foodtruck.getName();
        this.accountInfo = foodtruck.getAccountInfo();
        this.carNumber = foodtruck.getCarNumber();
        this.phoneNumber = foodtruck.getPhoneNumber();
        this.category = category;
    }


//    public FoodtruckResDto(Long foodtruckId, String announcement, String accountInfo, String carNumber, String phoneNumber, String name, String picture) {
//        this.footruckId = foodtruckId;
//        this.announcement = announcement;
//        this.picture = picture;
//        this.name = name;
//        this.accountInfo = accountInfo;
//        this.carNumber = carNumber;
//        this.phoneNumber = phoneNumber;
//    }
//
//    public static FoodtruckResDto fromEntity(Foodtruck foodtruck) {
//        return new FoodtruckResDto(
//                foodtruck.getFoodtrucks().getId(),
//                foodtruck.getAnnouncement(),
//                foodtruck.getAccountInfo(),
//                foodtruck.getPhoneNumber(),
//                foodtruck.getCarNumber(),
//                foodtruck.getName(),
//                foodtruck.getPicture()
//        );
//    }

}
