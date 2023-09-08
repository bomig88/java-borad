package bomi.java.board.board.service;

import bomi.java.board.board.dto.ArticleForm;
import bomi.java.board.board.dto.MemberForm;
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
import java.util.stream.Collectors;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public ArrayList<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member find(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member create(MemberForm dto) {
        Member member = dto.toEntity();
        if (member.getId() != null) {
            return null;
        } else {
            return memberRepository.save(member);
        }
    }

    @Transactional
    public List<Member> bulk_create(List<MemberForm> dtos) {
        List<Member> members = dtos.stream()
                .map(MemberForm::toEntity)
                .collect(Collectors.toList());

        members.forEach(article -> memberRepository.save(article));

        return members;
    }

    public Member update(Long id, MemberForm dto) {
        Member article = dto.toEntity();
        log.info("id = {}, article = {}", id, article.toString());
        Member target = memberRepository.findById(id).orElse(null);
        if (target == null || !Objects.equals(article.getId(), id)) {
            log.info("id 정보를 확인해주세요.");
            return null;
        } else {
            target.patch(article);
            return memberRepository.save(target);
        }
    }

    public Member delete(Long id) {
        log.info("id = {}", id);
        Member target = memberRepository.findById(id).orElse(null);
        if (target == null) {
            log.info("id 정보를 확인해주세요.");
            return null;
        } else {
            memberRepository.delete(target);
            return target;
        }
    }
}
