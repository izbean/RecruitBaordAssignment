package com.recruit.assignment.controller;

import java.time.LocalDateTime;

import com.recruit.assignment.domain.board.Board;
import com.recruit.assignment.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.recruit.assignment.domain.board.service.BoardService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String getBoardList(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findByBoardList(pageable));
        return "/board/list";
    }

    @GetMapping("/form")
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

//	@PostMapping("/write")
//	public ResponseEntity<String> write(Board board) {
//		board.setCreatedDate(LocalDateTime.now());
//		boardRepository.save(board);
//		return new ResponseEntity<>("success", HttpStatus.CREATED);
//	}
//
//	@PutMapping("/update")
//	public ResponseEntity<String> update(Board board) {
//		board.setTitle(board.getTitle());
//		board.setContent(board.getContent());
//		board.setModifiedDate(LocalDateTime.now());
//		boardRepository.save(board);
//		return new ResponseEntity<>("success", HttpStatus.OK);
//	}
//
//	@DeleteMapping("/delete")
//	public ResponseEntity<String> delete(int idx) {
//		boardRepository.deleteById(idx);
//		return new ResponseEntity<>("success", HttpStatus.OK);
//	}

}
