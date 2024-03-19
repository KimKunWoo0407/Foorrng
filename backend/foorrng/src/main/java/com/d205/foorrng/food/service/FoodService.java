package com.d205.foorrng.food.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.food.repository.FavoritefoodRepository;
import com.d205.foorrng.user.entity.FavoriteFood;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter @Setter
@Service
@RequiredArgsConstructor
public class FoodService {

    private final UserRepository userRepository;
    private final FavoritefoodRepository favoritefoodRepository;

    public void saveFavoriteFood(List<String> FavoriteFoodList) {

        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();

        for (String food: FavoriteFoodList) {
            FavoriteFood favoriteFood = FavoriteFood.builder()
                    .user(user)
                    .menu(food)
                    .build();
            favoritefoodRepository.save(favoriteFood);
        }
        // 테스트용
        // List<FavoriteFood> favoriteFoodList = favoritefoodRepository.findAllByUser(user)
        //          .orElseThrow(() -> new Exceptions(ErrorCode.FOODLIST_NOT_EXIST));
    }

}
