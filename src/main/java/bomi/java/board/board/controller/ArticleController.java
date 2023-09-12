package bomi.java.board.board.controller;

import bomi.java.board.board.dto.ArticleForm;
import bomi.java.board.board.entity.Article;
import bomi.java.board.board.service.ArticleService;
import bomi.java.board.board.service.CommentService;
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
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm dto, RedirectAttributes attrs) {
        Article saved = articleService.create(dto);
        if (saved != null) {
            return "redirect:/articles/" + saved.getId();
        } else {
            attrs.addFlashAttribute("msg", "등록에 실패했습니다.");
            return "redirect:/articles";
        }
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
//        Optional<Article> articleEntity = articleRepository.findById(id);
        model.addAttribute("article", articleService.find(id));
        model.addAttribute("commentDtos", commentService.comments(id));
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        model.addAttribute("articleList", articleService.findAll());
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.find(id));
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm dto, RedirectAttributes attrs) {
        Article saved = articleService.update(dto.toEntity().getId(), dto);
        if (saved != null) {
            return "redirect:/articles/" + saved.getId();
        } else {
            attrs.addFlashAttribute("msg", "id " + dto.toEntity().getId() + "수정에 실패했습니다.");
            return "redirect:/articles";
        }
    }

//    @DeleteMapping("/articles/{id}/delete")
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attrs) {
        Article saved = articleService.delete(id);
        if (saved != null) {
            attrs.addFlashAttribute("msg", "id " + id + "가 삭제되었습니다.");
        } else {
            attrs.addFlashAttribute("msg", "id " + id + "삭제에 실패했습니다.");
        }
        return "redirect:/articles";
    }
}
