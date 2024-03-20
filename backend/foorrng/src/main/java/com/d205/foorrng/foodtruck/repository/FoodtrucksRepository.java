package com.d205.foorrng.foodtruck.repository;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodtrucksRepository extends JpaRepository<Foodtrucks, Long> {

    Optional<Foodtrucks> findById(Long id);

    @Override
    List<Foodtrucks> findAll();
}
