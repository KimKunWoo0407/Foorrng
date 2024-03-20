package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateReqDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateReqDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.foodtruck.service.FoodtruckService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j // log
@RestController // rest api
@RequiredArgsConstructor // 생성자
@RequestMapping("/api/foodtruck") // url map
@Validated  // 유효성 검증
public class FoodtruckController {
    private final FoodtruckService foodtruckService;

    @ApiResponse(responseCode = "201", description = "점주 푸드트럭 등록 완료")
    @PostMapping(value = "/regist", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<? extends BaseResponseBody> createFoodtruck(
            @Valid @RequestPart(value = "foodtruckCreateDto") FoodtruckCreateReqDto foodtruckCreateReqDto,
            @RequestPart(value = "picture") MultipartFile picture) throws IOException {
        FoodtruckResDto foodtruckResDto = foodtruckService.createFoodtruck(foodtruckCreateReqDto, picture);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, foodtruckResDto));
    }

    @PatchMapping("/update")
    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 수정 완료")
    public ResponseEntity<? extends BaseResponseBody> updateFoodtruck(
            @Valid @RequestPart(value = "foodtruckUpdateReqDto", required = true) FoodtruckUpdateReqDto foodtruckUpdateReqDto,
            @RequestPart(value = "picuture", required = false) MultipartFile picture) throws IOException{
        System.out.println(foodtruckUpdateReqDto.getFoodtruckId());
        FoodtruckResDto foodtruckResDto = foodtruckService.updateFoodtruck(foodtruckUpdateReqDto, picture);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, foodtruckResDto));
    }

    @DeleteMapping("/delete/{foodtruckId}")
    @ApiResponse(responseCode = "201", description = "점주 푸드트럭 삭제 완료")
    public void deleteFoodtruck(@Valid @PathVariable("foodtruckId") FoodtruckId foodtruckId){
    }
}
