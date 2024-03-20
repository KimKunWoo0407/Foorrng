package com.d205.foorrng.foodtruck.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Getter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Foodtruck {
    @EmbeddedId
    private FoodtruckId foodtruckId;

    private String announcement;            // 공지사항

    private Long createdDay;               // 등록일

    private String picture;                 // 푸듣트럭 차 사진

    private String name;                    // 가게 이름

    private String accountInfo;            // 계봐 번호

    private String carNumber;              // 차량 번호

    private String phoneNumber;            // 연락처

    @Builder
    public Foodtruck(FoodtruckId foodtruckId,String announcement, Long createdDay, String picture, String name, String accountInfo, String carNumber, String phoneNumber){
        this.foodtruckId = foodtruckId;
        this.announcement = announcement;
        this.createdDay = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.picture = picture;
        this.name = name;
        this.accountInfo = accountInfo;
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
    }

    public void updateAnnouncement(String announcement) {
        this.announcement = announcement;
    }
    public void updatePicture(String picture) {
        this.picture = picture;
    }
    public void updateName(String name) {
        this.name = name;
    }
    public void updateAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }
    public void updateCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
