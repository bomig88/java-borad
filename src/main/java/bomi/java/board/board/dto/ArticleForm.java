package bomi.java.board.board.dto;

import bomi.java.board.board.entity.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    @Getter
    @JsonProperty("member_id")
    private Long memberId;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
