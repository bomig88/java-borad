package bomi.java.board.board.repository;

import bomi.java.board.board.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
