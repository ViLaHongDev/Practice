package com.practice.spsecurityjwt.controller;

import com.practice.spsecurityjwt.dto.ArticleDTO;
import com.practice.spsecurityjwt.dto.ArticleResponse;
import com.practice.spsecurityjwt.exception.NotFoundException;
import com.practice.spsecurityjwt.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity createArticle(@RequestBody ArticleDTO articleDTO){
        ArticleDTO article = articleService.createArticle(articleDTO);
        return new ResponseEntity(article,HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<ArticleResponse> getArticles(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return new ResponseEntity<>(articleService.getArticles(pageNo, pageSize), HttpStatus.OK);
    }
        @GetMapping("{id}")
        public ResponseEntity getArticalById(@PathVariable Integer id) {
            try {
                return ResponseEntity.ok(articleService.getArticleById(id));
            } catch (NotFoundException e) {
                return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
            }
        }

    @PutMapping("update/{id}")
    public ResponseEntity updateArticle(@PathVariable Integer id,@RequestBody ArticleDTO articleDTO){
        ArticleDTO articleUP = articleService.updateArticle(id,articleDTO);
        return ResponseEntity.ok(articleUP);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Integer id){
            articleService.deleteArticle(id);
            return new ResponseEntity<>("Delete Success",HttpStatus.OK);
    }
}
