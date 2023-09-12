package bomi.java.board.board.repository;

import bomi.java.board.board.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Slf4j
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    @DisplayName("게시글 전체 조회")
    void findAll() {
        List<Article> articles = articleRepository.findAll();
        assertNotNull(articles);
    }

}