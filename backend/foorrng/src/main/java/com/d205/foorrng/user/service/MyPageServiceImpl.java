package com.d205.foorrng.user.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
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
                if (foodtrucks.getFoodtruckRole().equals(FoodtruckRole.Foodtruck)) {
                    System.out.println(foodtrucks);
                    System.out.println(foodtrucks.getFoodtruck());
                    System.out.println(foodtrucks.getId());
                    List<FoodtruckResDto> foodtruckDtos = foodtrucks.getFoodtruck().stream()
                            .map(ft -> FoodtruckResDto.fromEntity(ft))
                            .collect(Collectors.toList());
                    return new FoodtrucksResDto(foodtrucks.getId(), FoodtruckRole.Foodtruck, foodtrucks,foodtruckDtos, null);
                } else {
                    List<FoodtruckRepResDto> foodtruckRepResDtos = foodtrucks.getFoodtruckReport().stream()
                            .map(ftr -> FoodtruckRepResDto.fromEntity(ftr))
                            .collect(Collectors.toList());
                    return new FoodtrucksResDto(foodtrucks.getId(), FoodtruckRole.FoodtruckReport, foodtrucks, null, foodtruckRepResDtos);
                }
            })
            .collect(Collectors.toList());
}

}
