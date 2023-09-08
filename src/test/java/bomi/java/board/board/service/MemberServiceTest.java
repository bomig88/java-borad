package bomi.java.board.board.service;

import bomi.java.board.board.dto.MemberForm;
import bomi.java.board.board.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    void findAll() {
        List<Member> members = memberService.findAll();
        assertNotNull(members);
    }

    @Test
    void find() {
        Long id = 1L;
        Member member = memberService.find(id);
        assertNotNull(member);
    }

    @Test
    @Transactional
    void create() {
        MemberForm dto = new MemberForm(null, "test타이틀", "text컨텐츠!");
        Member member = memberService.create(dto);
        assertNotNull(member);
    }

    @Test
    void bulk_create() {
        List<MemberForm> dtos = new ArrayList<MemberForm>();
        dtos.add(new MemberForm(null, "1test", "111111111"));
        dtos.add(new MemberForm(null, "2test", "2222222222"));

        List<Member> members = memberService.bulk_create(dtos);
        assertNotNull(members);
    }

    @Test
    void update() {
        Long id = 1L;
        MemberForm dto = new MemberForm(id, "update1", "update11111");
        Member member = memberService.update(id, dto);
        assertNotNull(member);
    }

    @Test
    @Transactional
    void delete() {
        Long id = 1L;
        Member member = memberService.delete(id);
        assertNotNull(member);
    }
}