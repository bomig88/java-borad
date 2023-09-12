package bomi.java.board.board.entity;

import bomi.java.board.board.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Entity
@Slf4j
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        log.info("id = {}, dto id = {}", dto.getArticleId(), article.getId());
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글의 id가 없어야 합니다.");
        if (!Objects.equals(dto.getArticleId(), article.getId()))
            throw new IllegalArgumentException("게시글의 id를 확인해주세요");
        return new Comment(
                null,
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        if (!Objects.equals(this.id, dto.getId()))
            throw new IllegalArgumentException("잘못된 id가 입력되었습니다.");
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
