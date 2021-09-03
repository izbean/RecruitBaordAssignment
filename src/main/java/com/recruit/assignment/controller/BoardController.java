package com.recruit.assignment.controller;

import java.time.LocalDateTime;

import com.recruit.assignment.domain.board.Board;
import com.recruit.assignment.domain.board.dto.BoardRequestDto;
import com.recruit.assignment.domain.board.dto.BoardResponseDto;
import com.recruit.assignment.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.recruit.assignment.domain.board.service.BoardService;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardList(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findByBoardList(pageable));
        return "/board/list";
    }

    @GetMapping("/create")
    public String form() {
        return "/board/form";
    }

    // 게시글 상세
    @GetMapping("/{boardContentId}")
    public String getBoardContentDetail(
            @PathVariable int boardContentId,
            Model model
    ) {
        model.addAttribute("ContentDetail", boardService.getBoardContentDetail(boardContentId));
        return "/board/detail";
    }

    @PostMapping
    @ResponseBody
    public BoardResponseDto.Response createBoardContents(
            @Valid BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal User user
    ) {
        return null;
    }

    @PutMapping("/{boardContentsId}")
    @ResponseBody
    public BoardResponseDto.Response updateBoardContents(
            @PathVariable long boardContentsId,
            @Valid BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal User user
    ) {
        return null;
    }

    @DeleteMapping("/{boardId}")
    @ResponseBody
    public BoardResponseDto.Response deleteBoard(
            @PathVariable long boardId,
            @AuthenticationPrincipal User user
    ) {
        return null;
    }

}
