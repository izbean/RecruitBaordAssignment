package com.recruit.assignment.controller;

import com.recruit.assignment.domain.board.dto.BoardCreateRequestDto;
import com.recruit.assignment.domain.board.dto.BoardResponseDto;
import com.recruit.assignment.domain.board.dto.BoardUpdateRequestDto;
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
            @Valid BoardCreateRequestDto boardCreateRequestDto,
            @AuthenticationPrincipal User user
    ) {
        boardCreateRequestDto.setUserId(user.getUsername());
        return boardService.create(boardCreateRequestDto);
    }

    @PutMapping("/{boardContentsId}")
    public Long update(
            @PathVariable long boardContentsId,
            @Valid BoardUpdateRequestDto boardUpdateRequestDto,
            @AuthenticationPrincipal User user
    ) {
        boardUpdateRequestDto.setUserId(user.getUsername());
        return boardService.update(boardUpdateRequestDto);
    }

    @DeleteMapping("/{boardId}")
    public Long delete(
            @PathVariable long boardId,
            @AuthenticationPrincipal User user
    ) {
        return boardService.delete(boardId, user.getUsername());
    }

}
