package com.d205.foorrng.Foodtrucks.entity;

import com.d205.foorrng.User.entity.User;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_seq")
    private Long id;

    private Boolean rv_is_delicious;    // 음식이 맛있어요

    private Boolean is_cool;            // 푸드트럭이 멋져요

    private Boolean is_clean;           // 매장이 청결해요

    private Boolean is_kind;            // 사장님이 친절해요

    private Boolean rv_id_special;      // 특별한 메뉴가 잇어요

    private Boolean rv_is_chip;         // 가성비가 좋아요

    private Boolean rv_is_fast;         // 음식이 빨리 나와요

    private Long created_date;          // 생성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodtruck_seq")
    private Foodtrucks foodtrucks;
}
