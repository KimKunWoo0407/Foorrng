package com.d205.foorrng.user.entity;

import com.d205.foorrng.article.entity.Article;
import com.d205.foorrng.foodtruck.entity.FoodtruckLike;
import com.d205.foorrng.requestDelete.RequestDelete;
import com.d205.foorrng.user.FavoriteFood;
import com.d205.foorrng.user.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long id;

    private Long userUid;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String acceessToken;       // 엑세스 토큰

    private String refreshToken;       // 리프레시 토큰

    private String fcmToken;           // fcm 토큰

    private String businessNumber;     // 사업자 번호

    @OneToMany(mappedBy = "user")
    private List<FavoriteFood> favoriteFoods;

    @OneToMany(mappedBy = "user")
    private List<FoodtruckLike> foodtruckLikes;

    @OneToMany(mappedBy = "user")
    private List<RequestDelete> requestDeleteList;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;
}
