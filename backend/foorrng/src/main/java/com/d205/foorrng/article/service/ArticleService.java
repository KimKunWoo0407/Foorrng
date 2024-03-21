package com.d205.foorrng.article.service;

import com.d205.foorrng.article.dto.request.ArticleReqDto;
import com.d205.foorrng.article.dto.response.ArticleListResDto;
import com.d205.foorrng.article.dto.response.ArticleResDto;
import com.d205.foorrng.article.entity.Article;
import com.d205.foorrng.common.model.BaseResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    //상세 기능들에 대해서 ServiceClass에 기능 구현하기;

//    //조회 하나의 값을 반환
//    Optional<ArticleResDto> getArticle(Long id);

    //조회 리스트로 반환
//    List<ArticleListResDto> getArticleList();


    //게시글 저장
    ResponseEntity<BaseResponseBody> saveArticle(ArticleReqDto article, MultipartFile image);



    //게시글 삭제
//    void removeArticle(Long id);
//
//
//    // 게시글 수정
//    void modifyArticleInfo(Long id, ArticleReqDto articleReqDto);


}
