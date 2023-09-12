package bomi.java.board.board.api;

import bomi.java.board.board.dto.CommentDto;
import bomi.java.board.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    CommentService commentService;
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.comments(articleId));
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.create(articleId, dto));
    }

    @PatchMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(id, dto));
    }

    @DeleteMapping("api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.delete(id));
    }
}
