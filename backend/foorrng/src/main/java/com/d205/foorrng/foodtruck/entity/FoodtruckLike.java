<<<<<<<< HEAD:backend/foorrng/src/main/java/com/d205/foorrng/Foodtrucks/entity/FoodtruckLike.java
package com.d205.foorrng.Foodtrucks.entity;

import com.d205.foorrng.User.entity.User;
========
package com.d205.foorrng.foodtruck.entity;

import com.d205.foorrng.user.User;
>>>>>>>> feature/API/foodtruck:backend/foorrng/src/main/java/com/d205/foorrng/foodtruck/entity/FoodtruckLike.java
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
public class FoodtruckLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodtruck_like_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodtrucks_seq")
    private Foodtrucks foodtrucks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;
}
