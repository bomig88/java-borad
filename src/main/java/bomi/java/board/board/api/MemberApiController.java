package bomi.java.board.board.api;

import bomi.java.board.board.dto.ArticleForm;
import bomi.java.board.board.dto.MemberForm;
import bomi.java.board.board.entity.Article;
import bomi.java.board.board.entity.Member;
import bomi.java.board.board.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class MemberApiController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/api/members")
    public ArrayList<Member> index() {
        return memberService.findAll();
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Member> show(@PathVariable Long id) {
        Member member = memberService.find(id);
        return (member == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/api/members")
    public ResponseEntity<Member> create(@RequestBody MemberForm dto) {
        Member member = memberService.create(dto);
        return (member == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/api/members/bulk")
    public ResponseEntity<List<Member>> bulk_create(@RequestBody List<MemberForm> dtos) {
        List<Member> articles = memberService.bulk_create(dtos);
        return (articles == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(articles);
    }

    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Member> edit(@PathVariable Long id, @RequestBody MemberForm dto) {
        Member member = memberService.update(id, dto);
        return (member == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Member> delete(@PathVariable Long id) {
        Member member = memberService.delete(id);
        return (member == null) ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
