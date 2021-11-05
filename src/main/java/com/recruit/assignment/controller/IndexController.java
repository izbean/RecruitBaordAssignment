package com.recruit.assignment.controller;

import com.recruit.assignment.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller("/")
@RequiredArgsConstructor
public class IndexController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.getByBoardList(pageable));
        return "/index";
    }

    @GetMapping("/board/create")
    public String form() {
        return "/board/form";
    }

    @GetMapping("/board/{boardContentId}")
    public String getBoardContentDetail(
            @PathVariable int boardContentId,
            Model model
    ) {
        model.addAttribute("ContentDetail", boardService.getBoardContentDetail(boardContentId));
        return "/board/detail";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

}
