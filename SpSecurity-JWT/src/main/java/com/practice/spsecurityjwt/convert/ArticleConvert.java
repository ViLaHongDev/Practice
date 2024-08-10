package com.practice.spsecurityjwt.convert;

import com.practice.spsecurityjwt.dto.ArticleDTO;
import com.practice.spsecurityjwt.models.Article;
public class ArticleConvert {

    public static Article toModel(ArticleDTO articleDTO){
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        return article;
    }
    public static ArticleDTO toDTO(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        return articleDTO;
    }
}
