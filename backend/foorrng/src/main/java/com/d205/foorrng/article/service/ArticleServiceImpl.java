package com.d205.foorrng.article.service;

import com.d205.foorrng.article.dto.request.ArticleReqDto;
import com.d205.foorrng.article.entity.Article;
import com.d205.foorrng.article.repository.ArticlePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl {

    @Autowired
    private ArticlePostRepository articlePostRepository;


    public Optional<List<Article>> findAllArticle(){
        return Optional.ofNullable(articlePostRepository.findAll());
    }

    public Optional<Article> findByArticle(Long articleId) {
        return articlePostRepository.findById(articleId);

    }

    public void removeArticle(Long articleId)
    {
        articlePostRepository.removeArticle();
    }

    public void saveArticle(ArticleReqDto articleReqDto){

    }


}
