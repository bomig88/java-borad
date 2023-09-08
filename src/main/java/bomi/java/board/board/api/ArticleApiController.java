package bomi.java.board.board.api;

import bomi.java.board.board.dto.ArticleForm;
import bomi.java.board.board.entity.Article;
import bomi.java.board.board.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public ArrayList<Article> index() {
        return articleService.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> show(@PathVariable Long id) {
        Article article = articleService.find(id);
        return (article == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article article = articleService.create(dto);
        return (article == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @PostMapping("/api/articles/bulk")
    public ResponseEntity<List<Article>> bulk_create(@RequestBody List<ArticleForm> dtos) {
        List<Article> articles = articleService.bulk_create(dtos);
        return (articles == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(articles);
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> edit(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article article = articleService.update(id, dto);
        return (article == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article article = articleService.delete(id);
        return (article == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(article);
    }
}
