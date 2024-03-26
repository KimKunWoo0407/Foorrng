package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.entity.Menu;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;
import com.d205.foorrng.foodtruck.service.FoodtruckService;
import com.d205.foorrng.foodtruck.service.MenuService;
import com.d205.foorrng.util.SecurityUtil;
import com.fasterxml.jackson.databind.ser.Serializers;
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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
@Validated
public class MenuController {

    private final MenuService menuService;
    private final FoodtruckService foodtrucksService;
    private final FoodtrucksRepository foodtrucksRepository;

    @PostMapping("/regist")
    @ApiResponse(responseCode = "201", description = "매뉴 생성 성공")
    public ResponseEntity<? extends BaseResponseBody> createMenu(
            Authentication authentication,
            @Valid @RequestPart("menuRequestDto") @Parameter(name = "menuRequestDto", description = "메뉴 정보") MenuRequestDto menuRequestDto,
            @RequestPart(value = "picture", required = false) @Parameter(name = "picture", description = "업로드 하고자 하는 메뉴 사진 파일") MultipartFile picture
    ) throws IOException {

        Long userUid = Long.parseLong(SecurityUtil.getCurrentUsername().get());

        // 사용자 기반으로 푸드트럭 번호 찾기
        Optional<Foodtrucks> foodtruck = foodtrucksRepository.findByUserUserUid(userUid);


        // 푸드 트럭의 메뉴 생성
        MenuResDto menuId = menuService.createMenu(foodtruck, menuRequestDto, picture);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(BaseResponseBody.of(0, menuId));
    }

    // 모든 메뉴 조회
    @GetMapping("")
    @ApiResponse(responseCode = "200", description = "전체 매뉴 조회 성공")
    public ResponseEntity<? extends BaseResponseBody> menuList(){
        return ResponseEntity.status(HttpStatus.SC_OK).body(BaseResponseBody.of(0, menuService.findAllMenus()));
    }

    // 해당 푸드트럭의 메뉴 조회
    @GetMapping("/{foodtruckId}")
    @ApiResponse(responseCode = "200", description = "푸드트럭 매뉴 조회 성공")
    public ResponseEntity<? extends BaseResponseBody> getMenusByFoodTruck(@PathVariable("foodtruckId") Long foodtrucksSeq) {
        return ResponseEntity.status(HttpStatus.SC_OK).body(BaseResponseBody.of(0, menuService.getMenus(foodtrucksSeq)));
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    @ApiResponse(responseCode = "204", description = "푸드트럭 메뉴 삭제 성공")
    public ResponseEntity<? extends BaseResponseBody> deleteMenu(@Valid @PathVariable("menuId") Long menuSeq){
        menuService.deleteMenu(menuSeq);
        return ResponseEntity.status(HttpStatus.SC_NO_CONTENT).body(BaseResponseBody.of(0, "success"));
    }

    // 메뉴 수정
    @PatchMapping("/{menuId}")
    @ApiResponse(responseCode = "200", description = "푸드트럭 메뉴 수정 성공")
    public ResponseEntity<? extends BaseResponseBody> modifyMenu(
            @PathVariable("menuId") Long menuSeq,
            @Valid @RequestPart("menuRequestDto") MenuRequestDto menuRequestDto,
            @RequestPart(value = "picture", required=false) MultipartFile picture) throws IOException {

        MenuResDto menuResDto = menuService.updateMenu(menuSeq, menuRequestDto, picture);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(BaseResponseBody.of(0, menuResDto));
    }

}
