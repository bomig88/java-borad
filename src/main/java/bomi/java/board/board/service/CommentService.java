package bomi.java.board.board.service;

import bomi.java.board.board.dto.CommentDto;
import bomi.java.board.board.entity.Article;
import bomi.java.board.board.entity.Comment;
import bomi.java.board.board.repository.ArticleRepository;
import bomi.java.board.board.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId){
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        log.info(dto.toString());
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("대상 게시물이 없습니다."));
        Comment comment = Comment.createComment(dto, article);
        commentRepository.save(comment);
        return CommentDto.createCommentDto(comment);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));
        target.patch(dto);
        Comment updated = commentRepository.save(target);
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
