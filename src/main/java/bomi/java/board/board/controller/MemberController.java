package bomi.java.board.board.controller;

import bomi.java.board.board.dto.MemberForm;
import bomi.java.board.board.entity.Member;
import bomi.java.board.board.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/members/create")
    public String createMember(MemberForm dto, RedirectAttributes attrs) {
        Member saved = memberService.create(dto);
        if (saved != null) {
            return "redirect:/members/" + saved.getId();
        } else {
            attrs.addFlashAttribute("msg", "등록에 실패했습니다.");
            return "redirect:/members";
        }
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.find(id));
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        model.addAttribute("memberList", memberService.findAll());
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.find(id));
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm dto, RedirectAttributes attrs) {
        Member saved = memberService.update(dto.toEntity().getId(), dto);
        if (saved != null) {
            return "redirect:/members/" + saved.getId();
        } else {
            attrs.addFlashAttribute("msg", "id " + dto.toEntity().getId() + "수정에 실패했습니다.");
            return "redirect:/members";
        }
    }

//    @DeleteMapping("/members/{id}/delete")
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attrs) {
        Member saved = memberService.delete(id);
        if (saved != null) {
            attrs.addFlashAttribute("msg", "id " + id + "가 삭제되었습니다.");
        } else {
            attrs.addFlashAttribute("msg", "id " + id + "삭제에 실패했습니다.");
        }
        return "redirect:/members";
    }
}
