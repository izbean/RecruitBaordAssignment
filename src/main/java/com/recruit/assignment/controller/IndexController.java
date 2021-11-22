package com.recruit.assignment.controller;

import com.recruit.assignment.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping
    public String index(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", postService.getByBoardList(pageable));
        model.addAttribute("postCount", postService.getPostCount());
        return "index";
    }

    @GetMapping("post/create")
    public String form() {
        return "post/form";
    }

    @GetMapping("post/{postId}")
    public String getBoardContentDetail(
            @PathVariable long postId,
            Model model
    ) {
        model.addAttribute("ContentDetail", postService.getPostDetail(postId));
        return "post/detail";
    }

    @RequestMapping("user/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("user/register")
    public String register() {
        return "user/register";
    }

}
