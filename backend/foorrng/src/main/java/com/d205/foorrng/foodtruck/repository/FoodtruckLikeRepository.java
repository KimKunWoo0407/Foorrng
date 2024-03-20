package com.d205.foorrng.foodtruck.repository;

import com.d205.foorrng.foodtruck.entity.FoodtruckLike;
import com.d205.foorrng.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodtruckLikeRepository extends JpaRepository<FoodtruckLike, Long> {

    Optional<List<FoodtruckLike>> findAllByUser(User user);

}
