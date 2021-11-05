package com.recruit.assignment.controller;

import com.recruit.assignment.domain.board.dto.request.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.recruit.assignment.domain.board.service.BoardService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public Long create(
            @Valid BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal User user
    ) {
        return boardService.create(boardRequestDto, user.getUsername());
    }

    @PutMapping("/{id}")
    public Long update(
            @PathVariable long id,
            @Valid BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal User user
    ) {
        return boardService.update(id, boardRequestDto, user.getUsername());
    }

    @DeleteMapping("/{id}")
    public Long delete(
            @PathVariable long id,
            @AuthenticationPrincipal User user
    ) {
        return boardService.delete(id, user.getUsername());
    }

}
