package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.service.FoodtruckService;
import com.d205.foorrng.user.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@Slf4j // log
@RestController // rest api
@RequiredArgsConstructor // 생성자
@RequestMapping("/api/foodtruck") // url map
@Validated  // 유효성 검증
public class FoodtruckController {

    private final FoodtruckService foodtruckService;


    @PostMapping("/regist")
    @ApiResponse(responseCode = "201", description = "푸드트럭 생성 성공")
    public void createFoodtruck(@Valid @RequestBody FoodtruckCreateDto foodtruckCreateDto){
        // trucks regist


        // truck regist

    }
}
