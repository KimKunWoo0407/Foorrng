package com.d205.foorrng.article.repository;


import com.d205.foorrng.article.Article;
import com.d205.foorrng.article.entity.ArticleEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public interface ArticlePostRepository extends JpaRepository<ArticleEntity, Long>{


    List<ArticleEntity> findAllArticle();



}
