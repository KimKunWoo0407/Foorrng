package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;
import com.d205.foorrng.foodtruck.service.FoodtrucksService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodtrucks")
public class FoodtrucksController {
//    private final FoodtrucksService foodtrucksService;

    @ApiResponse(responseCode = "200", description = "소비자 푸드트럭 전체 조회")
    @GetMapping("")
    public ResponseEntity<? extends BaseResponseBody> findAllByFoodtrucksToCustomer(@Valid @RequestBody FoodtrucksReqDto foodtrucksReqDto){
        // 모든 푸드트럭
        // 푸드트럭의 이름, 이미지, 푸드트럭 타입(점주/제보), 음식카테고리, 리뷰개수, 찜여부, 운영여부
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }

    @ApiResponse(responseCode = "200", description = "소비자 푸드트럭 상세 조회")
    @GetMapping("/detail")
    public ResponseEntity<? extends BaseResponseBody> findByFoodtrucksToCustomer(){

        // 모든 푸드트럭
        // 푸드트럭의 이름, 이미지, 푸드트럭 타입(점주/제보), 음식카테고리, 리뷰개수, 찜여부, 운영여부
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }


    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 조회")
    @GetMapping("/owner")
    public ResponseEntity<? extends BaseResponseBody> findByFoodtrucksToOwner(){

        // 모든 푸드트럭
        // 푸드트럭의 이름, 이미지, 푸드트럭 타입(점주/제보), 음식카테고리, 리뷰개수, 찜여부, 운영여부
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }


    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 운영관리")
    @GetMapping("/ownerz/operinfo")
    public ResponseEntity<? extends BaseResponseBody> findOperByFoodtrucksToOwner(){

        // 모든 푸드트럭
        // 푸드트럭의 이름, 이미지, 푸드트럭 타입(점주/제보), 음식카테고리, 리뷰개수, 찜여부, 운영여부
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }

}
