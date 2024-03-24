package com.d205.foorrng.user.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.FoodtruckLike;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckLikeRepository;
import com.d205.foorrng.foodtruck.response.FoodtruckRepResDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.foodtruck.response.FoodtrucksResDto;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final UserRepository userRepository;
    private final FoodtruckLikeRepository foodtruckLikeRepository;

    @Override
    public List<FoodtrucksResDto> getLikeFoodtrucks(Long user_seq) {
        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get()))
                .orElseThrow(() -> new Exceptions(ErrorCode.USER_NOT_EXIST));

        List<FoodtruckLike> likes = foodtruckLikeRepository.findAllByUser(user)
                .orElseThrow(() -> new Exceptions(ErrorCode.LIKES_NOT_FOUND));

        return likes.stream()
                .map(like -> {
                    Foodtrucks foodtrucks = like.getFoodtrucks();
                    List<FoodtruckResDto> foodtruckDtos = null;
                    List<FoodtruckRepResDto> foodtruckRepResDtos = null;
                    if (foodtrucks.getFoodtruckRole().equals(FoodtruckRole.Foodtruck)) {
                        foodtruckDtos = List.of(FoodtruckResDto.fromEntity(foodtrucks.getFoodtruck()));
                        return new FoodtrucksResDto(foodtrucks.getId(), foodtrucks.getFoodtruckRole(), foodtruckDtos, null);
                    } else {
                        foodtruckRepResDtos = List.of(FoodtruckRepResDto.fromEntity(foodtrucks.getFoodtruck()));
                        return new FoodtrucksResDto(foodtrucks.getId(), foodtrucks.getFoodtruckRole(), null, foodtruckRepResDtos);
                    }
                })
                .collect(Collectors.toList());
    }
}
