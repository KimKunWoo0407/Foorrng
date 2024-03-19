package com.d205.foorrng.article.repository;



import com.d205.foorrng.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticlePostRepository extends JpaRepository<Article, Long>{

    //하나 반환
    @Override
    Optional<Article> findById(Long aLong);

    //리스트 반환
    @Override
    List<Article> findAll();

    //수정 반환


    //삭제
    void removeArticle();

    //등록
    void saveArticle();

}
