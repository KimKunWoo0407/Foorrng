package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.FoodtruckReport;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodtruckRepResDto {
    private Long foodtruckId;
    private String announcement;            // 공지사항
    private Long createdDay;               // 등록일
    private String picture;                 // 푸듣트럭 차 사진
    private String name;                    // 가게 이름
    private String accountInfo;            // 계봐 번호
    private String carNumber;              // 차량 번호
    private String phoneNumber;            // 연락처
    private List<String> category;


    public FoodtruckRepResDto(FoodtruckReport foodtruckReport, Long id, Long createdDay, List<String> category){
        this.foodtruckId = id;
        this.announcement = foodtruckReport.getAnnouncement();
        this.createdDay = createdDay;
        this.picture = foodtruckReport.getPicture();
        this.name = foodtruckReport.getName();
        this.accountInfo = foodtruckReport.getAccountInfo();
        this.carNumber = foodtruckReport.getCarNumber();
        this.phoneNumber = foodtruckReport.getPhoneNumber();
        this.category = category;
    }
}
