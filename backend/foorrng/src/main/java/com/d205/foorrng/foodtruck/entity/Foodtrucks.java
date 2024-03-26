package com.d205.foorrng.foodtruck.entity;

import com.d205.foorrng.food.Food;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.requestDelete.RequestDelete;
import com.d205.foorrng.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.d205.foorrng.user.repository.UserRole.OWNER;

@Entity
@Getter
@NoArgsConstructor
@Table(name="foodtrucks")
public class Foodtrucks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foodtrucks_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FoodtruckRole foodtruckRole;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Foodtruck> foodtruck;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodtruckReport> foodtruckReport;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Food> foods;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodtruckLike> foodtruckLikes;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menuList;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RequestDelete> requestDeleteList;

    @OneToMany(mappedBy = "foodtrucks", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mark> markList;

    @Builder
    public Foodtrucks(FoodtruckRole foodtruckRole, User user){
        this.foodtruckRole = foodtruckRole;
        this.user = user;
    }
}
