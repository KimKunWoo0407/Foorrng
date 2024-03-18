package com.d205.foorrng.foodtruck.repository;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodtrucksRepository extends JpaRepository<Foodtrucks, Long> {
    Optional<Foodtrucks> findbyId(Long foodTrucksId);
}
