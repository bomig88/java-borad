package bomi.java.board.board.repository;

import bomi.java.board.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value="SELECT * FROM comment WHERE article_id=:articleId", nativeQuery=true)
    List<Comment> findByArticleId(Long articleId);

    List<Comment> findByNickname(String nickname);
}
