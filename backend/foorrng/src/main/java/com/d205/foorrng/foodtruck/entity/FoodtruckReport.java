package com.d205.foorrng.foodtruck.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodtruckReport {
    @EmbeddedId
    private FoodtruckReportId foodtruckId; // 푸드트럭s Id 식별관계
    private String name;            // 가게 이름
    private Long createdDay;       // 제보 등록일
    private String picture;         // 푸드트럭 사진
    private String carNumber;      // 차량 번호
    private String announcement;    // 가게 소개
    private String accountInfo;    // 계좌 번호
    private String phoneNumber;    // 연락처


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="foodtrucks_seq")
    private Foodtrucks foodtrucks;

    @Builder
    public FoodtruckReport(FoodtruckReportId reportId, String name, String picture, String carNumber, String announcement, String accountInfo, String phoneNumber){
        this.foodtruckId = reportId;
        this.name = name;
        this.picture = picture;
        this.carNumber = carNumber;
        this.announcement = announcement;
        this.accountInfo = accountInfo;
        this.phoneNumber = phoneNumber;
    }

    public void updateName(String name){
        this.name = name;
    }
    public void updatePicture(String picture){
        this.picture = picture;
    }
    public void updateCarNumber(String carNumber){
        this.carNumber = carNumber;
    }
    public void updateAnnouncement(String announcement){
        this.announcement = announcement;
    }
    public void updateAccountInfo(String accountInfo){
        this.accountInfo = accountInfo;
    }
    public void updatePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
