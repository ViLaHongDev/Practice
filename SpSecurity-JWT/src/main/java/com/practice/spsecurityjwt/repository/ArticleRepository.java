package com.practice.spsecurityjwt.repository;

import com.practice.spsecurityjwt.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

}
