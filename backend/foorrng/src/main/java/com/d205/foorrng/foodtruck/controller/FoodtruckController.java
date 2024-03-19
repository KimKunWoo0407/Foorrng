package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.foodtruck.service.FoodtruckService;
import com.d205.foorrng.user.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
            @Valid @RequestPart(value = "foodtruckCreateDto", required = true) FoodtruckCreateDto foodtruckCreateDto,
            @RequestPart(value = "picture", required = false) MultipartFile picture) throws IOException {
        System.out.println("controller");
        FoodtruckResDto foodtruckResDto = foodtruckService.createFoodtruck(foodtruckCreateDto, picture);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, foodtruckResDto));
    }

    @PatchMapping("/update")
    @ApiResponse(responseCode = "201", description = "점주 푸드트럭 수정 완료")
    public void updateFoodtruck(@Valid @RequestBody FoodtruckUpdateDto foodtruckUpdateDto){
    }

    @DeleteMapping("/delete/{foodtruckId}")
    @ApiResponse(responseCode = "201", description = "점주 푸드트럭 삭제 완료")
    public void deleteFoodtruck(@Valid @PathVariable("foodtruckId") FoodtruckId foodtruckId){
    }
}
