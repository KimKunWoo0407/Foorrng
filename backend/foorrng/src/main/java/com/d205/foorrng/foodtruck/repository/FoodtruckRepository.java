package com.d205.foorrng.foodtruck.repository;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodtruckRepository extends JpaRepository<Foodtruck, Long> {
}
