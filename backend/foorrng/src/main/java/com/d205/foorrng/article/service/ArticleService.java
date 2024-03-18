package com.d205.foorrng.article.service;

import com.d205.foorrng.article.entity.ArticleEntity;

public interface ArticleService {
    //상세 기능들에 대해서 ServiceClass에 기능 구현하기;


    //조회 하나의 값을 반환


    //조회 리스트로 반환
    void saveArticle(ArticleEntity article);

    //게시글 삭제
    void removeArticle(Long id);


    // 게시글 숮어
    void modifyArticleInfo(Long id, )


}
