package com.recruit.assignment.controller;

import com.recruit.assignment.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/")
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.getByBoardList(pageable));
        model.addAttribute("postCount", boardService.getPostCount());
        return "/index";
    }

    @GetMapping("/post/create")
    public String form() {
        return "/post/form";
    }

    @GetMapping("/post/{boardContentId}")
    public String getBoardContentDetail(
            @PathVariable int boardContentId,
            Model model
    ) {
        model.addAttribute("ContentDetail", boardService.getBoardContentDetail(boardContentId));
        return "/post/detail";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String loginFail() {
        return "/user/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

}
