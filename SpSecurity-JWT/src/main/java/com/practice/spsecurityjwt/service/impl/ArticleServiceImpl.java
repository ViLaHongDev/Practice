package com.practice.spsecurityjwt.service.impl;

import com.practice.spsecurityjwt.convert.ArticleConvert;
import com.practice.spsecurityjwt.dto.ArticleDTO;
import com.practice.spsecurityjwt.dto.ArticleResponse;
import com.practice.spsecurityjwt.exception.NotFoundException;
import com.practice.spsecurityjwt.models.Article;
import com.practice.spsecurityjwt.repository.ArticleRepository;
import com.practice.spsecurityjwt.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDTO createArticle(ArticleDTO articleDTONew) {

        Article createArticle = articleRepository.save(ArticleConvert.toModel(articleDTONew));

        return ArticleConvert.toDTO(createArticle);
    }

    @Override
    public ArticleResponse getArticles(int pageNo, int pageSize) {

        Pageable page = PageRequest.of(pageNo,pageSize);
        Page<Article> articles = articleRepository.findAll(page);

        //

        List<Article> listOfArticle = articles.getContent();
        List<ArticleDTO> content =
                listOfArticle.stream()
                        .map( article -> ArticleConvert.toDTO(article)).collect(Collectors.toList());
        //

        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setContent(content);
        articleResponse.setPageNo(pageNo);
        articleResponse.setPageSize(pageSize);
        articleResponse.setTotalElement(articles.getTotalElements());
        articleResponse.setTotalPages(articles.getTotalPages());
        articleResponse.setLast(articles.isLast());

        return articleResponse;
    }

    @Override
    public ArticleDTO getArticleById(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found article by id "+id));
        return ArticleConvert.toDTO(article);
    }

    @Override
    public ArticleDTO updateArticle(Integer id, ArticleDTO articleDTO) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found articles by id "+id));
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        Article articleUP = articleRepository.save(article);
        return ArticleConvert.toDTO(articleUP);
    }

    @Override
    public void deleteArticle(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found article by id"+id));
        articleRepository.delete(article);
    }
}
