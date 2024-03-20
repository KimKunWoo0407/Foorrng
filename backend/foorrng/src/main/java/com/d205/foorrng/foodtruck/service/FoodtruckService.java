package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;

public interface FoodtruckService {

    // 푸드트럭 생성
    void createFoodtruck(FoodtruckCreateDto foodtruckCreateDto);

    // 푸드트럭 조회(검색 위치에서 푸드트럭 조회)
//    List<FoodtruckResDto> foodtrucks(double latitude, double longitude);

    // 푸드트럭 수정
    void updateFoodtruck(Long foodtruckId, FoodtruckCreateDto foodtruckCreateDto);

    // 푸드트럭 삭제
//    Long deleteFoodtruck(Long foodtruckId);

    // 이름으로 푸드트럭 찾기
    Long findFoodtruckByUserId(Long userUid);
}
