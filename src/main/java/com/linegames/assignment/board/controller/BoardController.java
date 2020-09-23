package com.linegames.assignment.board.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.linegames.assignment.board.entity.Board;
import com.linegames.assignment.board.repository.BoardRepository;
import com.linegames.assignment.board.service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardRepository boardRepository;
	
	@GetMapping("/list")
	public String list(@PageableDefault Pageable pageable, Model model) {
		model.addAttribute("boardList", boardService.findByBoardList(pageable));
		return "board/list";
	}
	
	@GetMapping("/form")
	public String form() {
		return "board/form";
	}
	
	// 게시글 상세
	@GetMapping("/detail")
	public String detail(@RequestParam(value = "idx", defaultValue = "0") int idx, Model model) {
		model.addAttribute("detail", boardRepository.findByIdx(idx));
		return "board/detail";
	}
	
	// 게시글 생성
	@PostMapping("/write")
	public ResponseEntity<String> insertByIdx(Board board) {
		board.setCreateDateTime(LocalDateTime.now());
		board.setCreateId(boardService.currentUserName());
		boardRepository.save(board);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	
	// 게시글 수정
	@PutMapping("/modify")
	public ResponseEntity<String> updateByIdx(Board board) {
		Board updateBoard = boardRepository.getOne(board.getIdx()); 
		updateBoard.setTitle(board.getTitle());
		updateBoard.setContent(board.getContent());
		updateBoard.setModifyDateTime(LocalDateTime.now());
		updateBoard.setModifyId(boardService.currentUserName());
		boardRepository.save(updateBoard);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	// 게시글 삭제
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteByIdx(int idx) {
		boardRepository.deleteById(idx);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
}
