package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.response.LikeFoodtrucksDto;
import com.d205.foorrng.foodtruck.service.LikeFoodtruckService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodtrucks")
@Validated
public class FoodtrucksController {
    private final LikeFoodtruckService likeFoodtruckService;
    // 푸드트럭 좋아요/ 취소
    @PostMapping(value="/like/{foodtruckId}")
    @ApiResponse(responseCode = "200", description = "찜 성공")
    public ResponseEntity<String> like(Authentication authentication, @PathVariable("foodtruckId") Long foodtruckSeq){
        LikeFoodtrucksDto likeFoodtrucksDto = likeFoodtruckService.LikeFoodtruck(foodtruckSeq);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, likeFoodtrucksDto).toString());
    }
}
