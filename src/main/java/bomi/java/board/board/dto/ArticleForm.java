package bomi.java.board.board.dto;

import bomi.java.board.board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
