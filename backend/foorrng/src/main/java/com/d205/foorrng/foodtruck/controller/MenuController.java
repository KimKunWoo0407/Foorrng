package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.entity.Menu;
import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;
import com.d205.foorrng.foodtruck.service.FoodtruckService;
import com.d205.foorrng.foodtruck.service.MenuService;
import com.d205.foorrng.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
@Validated
public class MenuController {

    private final MenuService menuService;
    private final FoodtruckService foodtrucksService;

    @PostMapping("/regist")
    @ApiResponse(responseCode = "201", description = "매뉴 생성 성공")
    public ResponseEntity<? extends BaseResponseBody> createMenu(
            Authentication authentication,
            @Valid @RequestPart("menuRequestDto") @Parameter(name = "menuRequestDto", description = "메뉴 정보") MenuRequestDto menuRequestDto,
            @RequestPart(value = "picture", required = false) @Parameter(name = "picture", description = "업로드 하고자 하는 메뉴 사진 파일") MultipartFile picture
    ) {
        Long userUid = Long.parseLong(SecurityUtil.getCurrentUsername().get());

        // 사용자 기반으로 푸드트럭 번호 찾기
        Long foodtruck = foodtrucksService.findFoodtruckByUserId(userUid);
        System.out.println(foodtruck);


        // 푸드 트럭의 메뉴 생성
        MenuResDto menuId = menuService.createMenu(foodtruck, menuRequestDto, picture);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(BaseResponseBody.of(0, menuId));
    }

    // 모든 메뉴 조회
    @GetMapping("")
    @ApiResponse(responseCode = "200", description = "전체 매뉴 조회 성공")
    public ResponseEntity<List<MenuResDto>> menuList(){
        return new ResponseEntity<>(menuService.findAllMenus(), HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }

    // 해당 푸드트럭의 메뉴 조회
    @GetMapping("/{foodtruckId}")
    @ApiResponse(responseCode = "200", description = "푸드트럭 매뉴 조회 성공")
    public List<MenuResDto> getMenusByFoodTruck(@PathVariable("foodtruckId") Long foodtrucksSeq) {
        return menuService.getMenus(foodtrucksSeq);
    }

}
