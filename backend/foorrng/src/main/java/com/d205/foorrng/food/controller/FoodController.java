package com.d205.foorrng.food.controller;


import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.food.dto.FavoritefoodDto;
import com.d205.foorrng.food.repository.FavoritefoodRepository;
import com.d205.foorrng.food.service.FoodService;
import com.d205.foorrng.user.entity.FavoriteFood;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter @Setter
@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final FavoritefoodRepository favoritefoodRepository;

    @PostMapping("/favorite")
    public ResponseEntity<? extends BaseResponseBody> postFavoriteFoods(@RequestBody @Valid FavoritefoodDto favoritefoodDto) {

        // test용
        // List<FavoriteFood> favoriteFoodList = foodService.saveFavoriteFood(favoritefoodDto.getFavoriteFoods());
        foodService.saveFavoriteFood(favoritefoodDto.getFavoriteFoods());
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, null));

    }
}
