package com.d205.foorrng.review;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    List<Review> findByFoodtrucksId(Long foodtrucksSeq);

    Optional<Review> findTopByUserAndFoodtrucksOrderByCreatedDateDesc(User user, Foodtrucks foodtrucks);
    Optional<Review> findTopByUserOrderByCreatedDateDesc(User user);
}
