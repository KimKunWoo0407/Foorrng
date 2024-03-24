package com.d205.foorrng.mark.repository;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.mark.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findById(Long id);

    List<Mark> findAllByLatitudeBetweenAndLongitudeBetween(double latitude1, double longitude1, double latitude2, double longitude2);
    // list는 optional을 권장하지 않음. null로 처리가 되기 때문
    // list가 아닌 것은 exception으로 빠져서, optional을 사용한다고 함.

    List<Mark> findByFoodtrucks(Foodtrucks foodtrucks);
}
