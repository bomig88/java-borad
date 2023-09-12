package bomi.java.board.board.service;

import bomi.java.board.board.dto.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    void comments() {
        List<CommentDto> comments = commentService.comments(4L);
        assertNotNull(comments);
    }

    @Test
    @Transactional
    void create() {
        Long articleId = 4L;
        CommentDto dto = new CommentDto(null, articleId, "Tester", "헬로우!!");
        CommentDto commentDto = commentService.create(articleId, dto);
        assertNotNull(commentDto);
    }

    @Test
    void update() {
        Long id = 1L;
        Long articleId = 4L;
        CommentDto dto = new CommentDto(id, articleId, "Tester", "update11111");
        CommentDto commentDto = commentService.update(id, dto);
        assertNotNull(commentDto);
    }

    @Test
    @Transactional
    void delete() {
        Long id = 1L;
        CommentDto commentDto = commentService.delete(id);
        assertNotNull(commentDto);
    }
}