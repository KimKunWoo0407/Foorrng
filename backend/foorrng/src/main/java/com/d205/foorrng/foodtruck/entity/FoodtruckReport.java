package com.d205.foorrng.foodtruck.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodtruckReport {
    @EmbeddedId
    private FoodtruckReportId id; // 푸드트럭s Id 식별관계

    private String name;            // 가게 이름

    private Long createdDay;       // 제보 등록일

    private String picture;         // 푸드트럭 사진

    private String carNumber;      // 차량 번호

    private String announcement;    // 가게 소개

    private String accountInfo;    // 계좌 번호

    private String phoneNumber;    // 연락처

}
