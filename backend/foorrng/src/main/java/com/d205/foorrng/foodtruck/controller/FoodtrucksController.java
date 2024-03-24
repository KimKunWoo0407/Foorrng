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
import com.d205.foorrng.foodtruck.response.LikeFoodtrucksDto;
import com.d205.foorrng.foodtruck.service.LikeFoodtruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodtrucks")
public class FoodtrucksController {
//    private final FoodtrucksService foodtrucksService;
    private final LikeFoodtruckService likeFoodtruckService;

    @ApiResponse(responseCode = "200", description = "소비자 푸드트럭 전체 조회")
    @GetMapping("")
    public ResponseEntity<? extends BaseResponseBody> findAllByFoodtrucksToCustomer(@Valid @RequestBody FoodtrucksReqDto foodtrucksReqDto){
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }

    @ApiResponse(responseCode = "200", description = "소비자 푸드트럭 상세 조회")
    @GetMapping("/detail")
    public ResponseEntity<? extends BaseResponseBody> findByFoodtrucksToCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }


    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 조회")
    @GetMapping("/owner")
    public ResponseEntity<? extends BaseResponseBody> findByFoodtrucksToOwner(){
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }


    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 운영관리")
    @GetMapping("/ownerz/operinfo")
    public ResponseEntity<? extends BaseResponseBody> findOperByFoodtrucksToOwner(){
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,""));
    }

    
    // 푸드트럭 좋아요
    @PostMapping(value="/like/{foodtruckId}")
    @ApiResponse(responseCode = "200", description = "찜 성공")
    public ResponseEntity<String> like(Authentication authentication, @PathVariable("foodtruckId") Long foodtruckSeq){
        LikeFoodtrucksDto likeFoodtrucksDto = likeFoodtruckService.LikeFoodtruck(foodtruckSeq);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, likeFoodtrucksDto).toString());
    }
}
