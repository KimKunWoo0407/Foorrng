package com.d205.foorrng.Foodtrucks;

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
    private FookTruckRole fookTruckRole;

    @OneToOne(mappedBy = "foodtrucks", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private Foodtruck foodtruck;

    @OneToOne(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FoodtruckReport foodtruck_report;

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
