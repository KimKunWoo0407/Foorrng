package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtruckCreateReqDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateReqDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;

public interface FoodtruckService {

    // 푸드트럭 생성
    FoodtruckResDto createFoodtruck(FoodtruckCreateReqDto foodtruckCreateReqDto, MultipartFile picture) throws IOException;

    // 이미지 S3에 저장
    String saveImageS3(MultipartFile pictrue, String imgName, String dir) throws IOException;

    // 푸드트럭 수정
    FoodtruckResDto updateFoodtruck(FoodtruckUpdateReqDto foodtruckUpdateReqDto, MultipartFile picture) throws IOException;


    // 푸드트럭 삭제
    // Long deleteFoodtruck(Long foodtruckId);
//    Long deleteFoodtruck(Long foodtruckId);

}
