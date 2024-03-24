package com.d205.foorrng.food.repository;

import com.d205.foorrng.food.Food;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // 푸드트럭에 속하는 음식 카테고리 list로 받아오기
    List<Food> findAllByFoodtrucks(Foodtrucks foodtrucks);


}
