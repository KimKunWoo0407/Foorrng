package com.d205.foorrng.review;

import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@Validated
public class ReviewController {
    private final ReviewService reviewService;
    private final FoodtrucksRepository foodtrucksRepository;

    @PostMapping("/{foodtruckId}/regist")
    @ApiResponse(responseCode = "201", description = "리뷰 생성 성공")
    public ResponseEntity<? extends BaseResponseBody> createReview(
            @PathVariable("foodtruckId") Long foodtrucksSeq,
            @Valid @RequestPart("reviewReqDto") @Parameter(name ="reviewReqDto", description = "리뷰 정보") ReviewReqDto reviewReqDto){

        ReviewResDto reviewId = reviewService.createReview(foodtrucksSeq, reviewReqDto);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(BaseResponseBody.of(0, reviewId));
    }

    @GetMapping("/{foodtruckId}/list")
    @ApiResponse(responseCode = "200", description = "리뷰 리스트 조회 성공")
    public ReviewSummaryDto getReview(@PathVariable("foodtruckId") Long foodtruckSeq){
     return reviewService.getReviews(foodtruckSeq);
    }
}
