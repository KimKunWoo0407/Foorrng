package com.d205.foorrng.foodtruck.repository;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodtruckRepository extends JpaRepository<Foodtruck, Long> {
    // 한개 조회
    Optional<Foodtruck> findByFoodtruckId(FoodtruckId foodtruckId);

    // 전체 조회
}