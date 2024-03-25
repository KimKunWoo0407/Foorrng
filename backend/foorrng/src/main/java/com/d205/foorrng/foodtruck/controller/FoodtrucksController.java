package com.d205.foorrng.foodtruck.controller;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;
import com.d205.foorrng.foodtruck.response.FoodtrucksResDto;
import com.d205.foorrng.foodtruck.response.LikeFoodtrucksDto;
import com.d205.foorrng.foodtruck.service.FoodtrucksService;
import com.d205.foorrng.foodtruck.service.LikeFoodtruckService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodtrucks")
public class FoodtrucksController {
    private final FoodtrucksService foodtrucksService;
    private final LikeFoodtruckService likeFoodtruckService;


    @GetMapping("")
    @ApiResponse(responseCode = "200", description = "소비자 푸드트럭 전체 조회")
    public ResponseEntity<? extends BaseResponseBody> findAllByFoodtrucksToCustomer(@RequestBody FoodtrucksReqDto foodtrucksReqDto) throws IOException{
        List<FoodtrucksResDto> foodtrucksResDtos = foodtrucksService.foodtrucklist(foodtrucksReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,foodtrucksResDtos));
    }

    @GetMapping("/detail/{foodtrucksId}/{markId}")
    @ApiResponse(responseCode = "200", description = "소비자 푸드트럭 상세 조회")
    public ResponseEntity<? extends BaseResponseBody> findByFoodtrucksToCustomer(@PathVariable("foodtrucksId") Long foodtrucksId, @PathVariable("markId") Long markId) throws IOException {
        Map<String, Object> response = foodtrucksService.foodtruckdetail(foodtrucksId, markId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,response));
    }


    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 조회")
    @GetMapping("/owner/{foodtruckId}")
    public ResponseEntity<? extends BaseResponseBody> findByFoodtrucksToOwner(@PathVariable("foodtruckId") Long foodtruckId) throws IOException{
        Map<String, Object> response = foodtrucksService.myfoodtruck(foodtruckId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,response));
    }


    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 운영관리")
    @GetMapping("/owner/operinfo/{foodtruckId}")
    public ResponseEntity<? extends BaseResponseBody> findOperByFoodtrucksToOwner(@PathVariable("foodtruckId") Long foodtruckId) throws IOException{
        List<Map<String, Object>> response = foodtrucksService.myfoodtruckOper(foodtruckId);
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0,response));
    }

    
    // 푸드트럭 좋아요
    @PostMapping(value="/like/{foodtruckId}")
    @ApiResponse(responseCode = "200", description = "찜 성공")
    public ResponseEntity<String> like(Authentication authentication, @PathVariable("foodtruckId") Long foodtruckSeq){
        LikeFoodtrucksDto likeFoodtrucksDto = likeFoodtruckService.LikeFoodtruck(foodtruckSeq);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, likeFoodtrucksDto).toString());
    }
}
