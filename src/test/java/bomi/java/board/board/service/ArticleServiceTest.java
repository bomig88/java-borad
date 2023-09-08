package bomi.java.board.board.service;

import bomi.java.board.board.dto.ArticleForm;
import bomi.java.board.board.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void findAll() {
        List<Article> articles = articleService.findAll();
        assertNotNull(articles);
    }

    @Test
    void find() {
        Long id = 1L;
        Article article = articleService.find(id);
        assertNotNull(article);
    }

    @Test
    @Transactional
    void create() {
        ArticleForm dto = new ArticleForm(null, "test타이틀", "text컨텐츠!");
        Article article = articleService.create(dto);
        assertNotNull(article);
    }

    @Test
    void bulk_create() {
        List<ArticleForm> dtos = new ArrayList<ArticleForm>();
        dtos.add(new ArticleForm(null, "1test", "111111111"));
        dtos.add(new ArticleForm(null, "2test", "2222222222"));

        List<Article> articles = articleService.bulk_create(dtos);
        assertNotNull(articles);
    }

    @Test
    void update() {
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, "update1", "update11111");
        Article article = articleService.update(id, dto);
        assertNotNull(article);
    }

    @Test
    @Transactional
    void delete() {
        Long id = 1L;
        Article article = articleService.delete(id);
        assertNotNull(article);
    }
}