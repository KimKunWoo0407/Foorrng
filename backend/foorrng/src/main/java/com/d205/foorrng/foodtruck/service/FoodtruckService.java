package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FoodtruckService {

    // 푸드트럭 생성
    FoodtruckResDto createFoodtruck(FoodtruckCreateDto foodtruckCreateDto, MultipartFile picture) throws IOException;

    // 푸드트럭 수정
    void updateFoodtruck(Long foodtruckId, FoodtruckCreateDto foodtruckCreateDto);

    // 푸드트럭 삭제
    // Long deleteFoodtruck(Long foodtruckId);
}
