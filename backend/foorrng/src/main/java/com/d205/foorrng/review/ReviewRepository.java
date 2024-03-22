package com.d205.foorrng.review;

import com.d205.foorrng.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFoodtrucksId(Long foodtrucksSeq);

    Optional<Review> findTopByUserOrderByCreatedDateDesc(User user);
}
