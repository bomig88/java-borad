package bomi.java.board.board.service;

import bomi.java.board.board.dto.ArticleForm;
import bomi.java.board.board.entity.Article;
import bomi.java.board.board.entity.Member;
import bomi.java.board.board.repository.ArticleRepository;
import bomi.java.board.board.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private MemberRepository memberRepository;

    public ArrayList<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article find(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        Optional<Member> member = memberRepository.findById(dto.getMemberId());
        if (member.isPresent()) {
            article.setMember(member.get());
            if (article.getId() != null) {
                return null;
            } else {
                return articleRepository.save(article);
            }
        } else {
            return null;
        }
    }

    @Transactional
    public List<Article> bulk_create(List<ArticleForm> dtos) {
        List<Article> articles = dtos.stream()
                .map(ArticleForm::toEntity)
                .collect(Collectors.toList());

        articles.forEach(article -> articleRepository.save(article));

        return articles;
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id = {}, article = {}", id, article.toString());
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null || !Objects.equals(article.getId(), id)) {
            log.info("id 정보를 확인해주세요.");
            return null;
        } else {
            target.patch(article);
            return articleRepository.save(target);
        }
    }

    public Article delete(Long id) {
        log.info("id = {}", id);
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("id 정보를 확인해주세요.");
            return null;
        } else {
            articleRepository.delete(target);
            return target;
        }
    }
}
