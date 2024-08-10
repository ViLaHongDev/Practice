package com.practice.spsecurityjwt.service;

import com.practice.spsecurityjwt.dto.ArticleDTO;
import com.practice.spsecurityjwt.dto.ArticleResponse;
import com.practice.spsecurityjwt.models.Article;

import java.util.List;

public interface ArticleService {
        ArticleDTO createArticle(ArticleDTO articleDTONew);
        ArticleResponse getArticles(int pageNo, int pageSize);

        ArticleDTO updateArticle(Integer id,ArticleDTO articleDTO);
        ArticleDTO getArticleById(Integer id);
        void deleteArticle(Integer id);
}
