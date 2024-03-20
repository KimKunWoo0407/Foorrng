package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.foodtruck.entity.FoodtruckReportId;
import com.d205.foorrng.foodtruck.request.FoodtruckReportCreateReqDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateReqDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodtruck-report")
@Validated // 유효성 검증? 쓰는게 맞나?
public class FoodtruckReportController {

    @PostMapping("/regist")
    @ApiResponse(responseCode = "201", description = "제보 푸드트럭 등록 완료")
    public void createFoodtruckReport(@Valid @RequestBody FoodtruckReportCreateReqDto foodtruckReportCreateReqDto){
    }

    @PatchMapping("/update")
    @ApiResponse(responseCode = "200", description = "제보 푸드트럭 수정 완료")
    public void updateFoodtruckReport(@Valid @RequestBody FoodtruckUpdateReqDto foodtruckReportUpdateDto){
    }

    @DeleteMapping("/delete/{foodtruckReportId}")
    @ApiResponse(responseCode = "200", description = "제보 푸드트럭 삭제 완료")
    public void deleteFoodtruckReport(
            @Valid
            @PathVariable("foodtruckReportId") FoodtruckReportId foodtruckReportId){

    }
}
