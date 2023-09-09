package com.jojoldu.book.springbootwebservice.web;

import com.jojoldu.book.springbootwebservice.service.PostsService;
import com.jojoldu.book.springbootwebservice.web.dto.PostsListResponseDto;
import com.jojoldu.book.springbootwebservice.web.dto.PostsResponseDto;
import com.jojoldu.book.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springbootwebservice.web.dto.PostsUpdateRequestDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/save")
    public String postsSave(Model model) {
        model.addAttribute("posts", new PostsSaveRequestDto());
        return "posts-save";
    }

    @PostMapping("/save")
    public String postsSave(@RequestParam String title, @RequestParam String content, @RequestParam String author) {
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto(title, content, author);
        postsService.save(requestDto);
        return "redirect:/";
    }

    @GetMapping
    public String posts(Model model) {
        List<PostsListResponseDto> posts = postsService.findAll();
        model.addAttribute("posts", posts);
        return "posts-list";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(title, content);
        postsService.update(id, requestDto);
        return "redirect:/posts";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postsService.delete(id);
        return "redirect:/posts";
    }

    @PostConstruct
    public void init() {
        PostsSaveRequestDto requestDto1 = new PostsSaveRequestDto("제목1", "내용1", "작가1");
        PostsSaveRequestDto requestDto2 = new PostsSaveRequestDto("제목2", "내용2", "작가2");
        PostsSaveRequestDto requestDto3 = new PostsSaveRequestDto("제목3", "내용3", "작가3");
        postsService.save(requestDto1);
        postsService.save(requestDto2);
        postsService.save(requestDto3);
    }
}
