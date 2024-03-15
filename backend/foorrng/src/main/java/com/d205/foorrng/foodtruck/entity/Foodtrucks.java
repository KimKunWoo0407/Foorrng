package com.d205.foorrng.foodtruck.entity;

import com.d205.foorrng.food.Food;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.menu.Menu;
import com.d205.foorrng.requestDelete.RequestDelete;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Validated // 사용 이유?
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 프록시 개념을 위해 protected
@Table(name="foodtrucks")
@AllArgsConstructor
public class Foodtrucks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodtruckRole foodtruckRole;

    @OneToMany(mappedBy = "foodtrucks")
    private List<Food> foods;

    @OneToMany(mappedBy = "foodtrucks")
    private List<FoodtruckLike> foodtruckLikes;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menuList;

    @OneToMany(mappedBy = "foodtrucks")
    private List<RequestDelete> requestDeleteList;

    @OneToMany(mappedBy = "foodtrucks")
    private List<Mark> markList;
}
