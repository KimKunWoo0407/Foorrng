package com.d205.foorrng.review;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.user.User;
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

    private Boolean rvIsDelicious;    // 음식이 맛있어요

    private Boolean isCool;            // 푸드트럭이 멋져요

    private Boolean isClean;           // 매장이 청결해요

    private Boolean isKind;            // 사장님이 친절해요

    private Boolean rvIdSpecial;      // 특별한 메뉴가 잇어요

    private Boolean rvIsChip;         // 가성비가 좋아요

    private Boolean rvIsFast;         // 음식이 빨리 나와요

    private Long createdDate;          // 생성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodtruck_seq")
    private Foodtrucks foodtrucks;
}
