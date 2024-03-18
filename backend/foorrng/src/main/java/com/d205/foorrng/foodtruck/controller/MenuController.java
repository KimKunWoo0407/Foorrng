package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;
import com.d205.foorrng.foodtruck.service.MenuService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
@Validated
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/regist")
    @ApiResponse(responseCode = "201", description = "매뉴 생성 성공")
    public ResponseEntity<? extends BaseResponseBody> createMenu(
            Authentication authentication,
            @Valid @RequestBody MenuRequestDto menuRequestDto,
            @RequestPart(value = "picture") @Parameter(name = "picture", description = "업로드 하고자 하는 메뉴 사진 파일") MultipartFile picture
    ) {

        // 로그인한 유저의 푸드트럭 조회
        Foodtrucks foodtruckId = (Foodtrucks) authentication.getPrincipal();

        // 푸드 트럭의 메뉴 생성
        MenuResDto menuId = menuService.createMenu(foodtruckId, picture, menuRequestDto);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(BaseResponseBody.of(0, menuId));
    }

}
