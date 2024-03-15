package com.d205.foorrng.foodtruck.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Foodtruck {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "foodtruck_seq")
//    private Long id;
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "foodtrucks_seq")
    private Foodtrucks foodtrucks;

    private String announcement;            // 공지사항

    private Long createdDay;               // 등록일

    private String picture;                 // 푸듣트럭 차 사진

    private String name;                    // 가게 이름

    private String accountInfo;            // 계봐 번호

    private String carNumber;              // 차량 번호

    private String phoneNumber;            // 연락처

    // 공지사항 변경
    public void changeAnnouncement(String announcement){
        this.announcement = announcement;
    }

    // 사진 변경
    public void changePicture(String picture){
        this.picture = picture;
    }

    // 계좌번호 변경
    public void changeAccountInfo(String accountInfo){
        this.accountInfo = accountInfo;
    }

    // 연락처 변경
    public void changePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
