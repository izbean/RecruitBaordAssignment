package com.recruit.assignment.controller;

import java.time.LocalDateTime;

import com.recruit.assignment.board.Board;
import com.recruit.assignment.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.recruit.assignment.board.service.BoardService;

@Controller
@RequestMapping("board/")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardRepository boardRepository;
	
	@GetMapping("/list")
	public String list(@PageableDefault Pageable pageable, Model model) {
		model.addAttribute("boardList", boardService.findByBoardList(pageable));
		return "/board/list";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/board/form";
	}
	
	// 게시글 상세
	@GetMapping("/detail")
	public String detail(
			@RequestParam(value = "idx", defaultValue = "0") int boardId,
			Model model
	) {
		model.addAttribute("detail", boardService.getBoardDetail(boardId));
		return "/board/detail";
	}

	@PostMapping("/write")
	public ResponseEntity<String> write(Board board) {
		board.setCreatedDate(LocalDateTime.now());
		boardRepository.save(board);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(Board board) {
		board.setTitle(board.getTitle());
		board.setContent(board.getContent());
		board.setModifiedDate(LocalDateTime.now());
		boardRepository.save(board);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(int idx) {
		boardRepository.deleteById(idx);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
}
