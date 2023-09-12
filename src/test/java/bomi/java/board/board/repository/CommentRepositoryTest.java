package bomi.java.board.board.repository;

import bomi.java.board.board.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        List<Comment> comments = commentRepository.findByArticleId(4L);
        assertNotNull(comments);

        comments = commentRepository.findByArticleId(1L);
        log.debug(comments.toString());
        assertNotNull(comments);
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        List<Comment> comments = commentRepository.findByNickname("Park");
        assertNotNull(comments);
    }
}