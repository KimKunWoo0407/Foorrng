package com.d205.foorrng.article.controller;


import com.d205.foorrng.article.dto.request.ArticleReqDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "Article ", description = "관련 api ")
@RestController
@Getter
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final UserRepository userRepository;

    @GetMapping("/list")
    @Operation(summary = "아티클 전체 조회", description = "게시글 관련된 목록을 전체 조회합니다. ")
    public List<ArticleResDto> articleList(){
        return articleService.getArticleList();
    }

    @PutMapping("/regist")
    @Operation(summary =  "게시글 등록", description = "게시글을 등록합니다.")
    @ApiResponse(responseCode = "201",description = "성공 /n/n Success 반환")
    public ResponseEntity<? extends BaseResponseBody> articleSave(
            @Valid @RequestBody ArticleReqDto articleReqDto
    ){
        Optional<User> user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername()
                .orElseThrow(() -> new Exceptions(ErrorCode.ARTICLE_NOT_EXIST))));

//      Long articleId = user.

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0,4));

    }
//      @GetMapping("/update")
//      @GetMapping("/delete")
}
