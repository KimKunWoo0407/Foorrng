package com.d205.foorrng.Foodtrucks;

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
public class Foodtruck {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "foodtruck_seq")
//    private Long id;
    @Id
    @OneToOne
    @JoinColumn(name = "foodtrucks_seq")
    private Foodtrucks foodtrucks;

    private String announcement;            // 공지사항

    private Long created_day;               // 등록일

    private String picture;                 // 푸듣트럭 차 사진

    private String name;                    // 가게 이름

    private String account_info;            // 계봐 번호

    private String car_number;              // 차량 번호

    private String phone_number;            // 연락처


}
