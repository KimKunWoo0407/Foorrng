package com.d205.foorrng.foodtruck.entity;

import com.d205.foorrng.food.Food;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.menu.Menu;
import com.d205.foorrng.requestDelete.RequestDelete;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Foodtrucks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodtrucks_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodTruckRole fookTruckRole;

    @OneToOne(mappedBy = "foodtrucks", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private Foodtruck foodtruck;

    @OneToOne(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FoodtruckReport foodtruckReport;

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
