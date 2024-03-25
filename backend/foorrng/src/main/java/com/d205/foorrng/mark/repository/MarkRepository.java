package com.d205.foorrng.mark.repository;

import com.d205.foorrng.mark.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findById(Long id);

    Optional<List<Mark>> findAllByFoodtrucksId(Long foodtruckId);

}
