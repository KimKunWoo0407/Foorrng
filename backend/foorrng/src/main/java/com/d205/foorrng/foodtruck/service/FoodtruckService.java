package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FoodtruckService {

    // 푸드트럭 생성
    FoodtruckResDto createFoodtruck(FoodtruckCreateDto foodtruckCreateDto, MultipartFile picture) throws IOException;

    // 이미지 S3에 저장
    String saveImageS3(MultipartFile pictrue, String imgName, String dir) throws IOException;

    // 푸드트럭 수정
    FoodtruckResDto updateFoodtruck(FoodtruckUpdateDto foodtruckUpdateDto, MultipartFile picture) throws IOException;

    // 푸드트럭 삭제
    // Long deleteFoodtruck(Long foodtruckId);
}
