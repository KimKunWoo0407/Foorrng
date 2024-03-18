package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.service.MenuService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
@Validated
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/regist")
    @ApiResponse(responseCode = "201", description = "매뉴 생성 성공")
    public void createMenu(@Valid @RequestBody MenuRequestDto menuResquestDto){
        // menu regist

    }
}
