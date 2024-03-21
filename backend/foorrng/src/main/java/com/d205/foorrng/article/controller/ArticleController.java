package com.d205.foorrng.article.controller;


import com.d205.foorrng.article.dto.request.ArticleReqDto;
import com.d205.foorrng.article.dto.response.ArticleListResDto;
import com.d205.foorrng.article.dto.response.ArticleResDto;
import com.d205.foorrng.article.service.ArticleService;
import com.d205.foorrng.article.service.ArticleServiceImpl;
import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "Article ", description = "관련 api ")
@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;


//    @ApiResponse(responseCode = "201", description = "점주 푸드트럭 등록 완료")
//    @PostMapping(value = "/regist", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<? extends BaseResponseBody> createFoodtruck(
//            @Valid @RequestPart(value = "foodtruckCreateDto") FoodtruckCreateReqDto foodtruckCreateReqDto,
//            @RequestPart(value = "picture") MultipartFile picture) throws IOException {
//        FoodtruckResDto foodtruckResDto = foodtruckService.createFoodtruck(foodtruckCreateReqDto, picture);
//        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, foodtruckResDto));
//    }
//
//    @PatchMapping("/update")
//    @ApiResponse(responseCode = "200", description = "점주 푸드트럭 수정 완료")
//    public ResponseEntity<? extends BaseResponseBody> updateFoodtruck(
//            @Valid @RequestPart(value = "foodtruckUpdateReqDto", required = true) FoodtruckUpdateReqDto foodtruckUpdateReqDto,
//            @RequestPart(value = "picuture", required = false) MultipartFile picture) throws IOException{
//        FoodtruckResDto foodtruckResDto = foodtruckService.updateFoodtruck(foodtruckUpdateReqDto, picture);
//        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, foodtruckResDto));
//    }
//
//    @DeleteMapping("/delete/{foodtruckId}")
//    @ApiResponse(responseCode = "201", description = "점주 푸드트럭 삭제 완료")
//    public ResponseEntity<? extends BaseResponseBody> deleteFoodtruck(@Valid @PathVariable("foodtruckId") Long foodtruckId){
//        foodtruckService.deleteFoodtruck(foodtruckId);
//        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, "success"));
//    }

    /**
     *   @GetMapping("/list")
     *     @Operation(summary = "아티클 전체 조회", description = "게시글 관련된 목록을 전체 조회합니다. ")
     *     public ResponseEntity<? extends BaseResponseBody> getAllArticles(){
     *         List<ArticleListResDto> articles = articleService.getArticleList();
     *         return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0,articles));
     *     }
     * */


    @PostMapping(value = "/regist", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "게시글 등록", description = "게시글을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "성공 /n/n Success 반환")
    public ResponseEntity<? extends BaseResponseBody> articleSave(
            @Valid @RequestPart(value = "articleReqDto") ArticleReqDto articleReqDto,
            @RequestPart(value = "picture") MultipartFile mainImage) {
//        try {
//            // 게시글 저장 로직 실행
//            articleService.saveArticle(articleReqDto, mainImage);
//            // 성공 응답 반환
//            return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(201, "Success"));
//        } catch (Exceptions e) {
//            // 커스텀 예외에 대한 처리
//            ErrorCode errorCode = e.getErrorCode();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(BaseResponseBody.error(errorCode.getErrorCode(), errorCode.getMessage()));
//        } catch (Exception e) {
//            // 기타 예외 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(BaseResponseBody.error("Internal Server Error", e.getMessage()));
//        }
        return articleService.saveArticle(articleReqDto, mainImage);
    }

//      @GetMapping("/update")
//      @GetMapping("/delete")
}
