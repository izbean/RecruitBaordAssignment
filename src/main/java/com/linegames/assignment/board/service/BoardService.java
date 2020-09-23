package com.linegames.assignment.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.linegames.assignment.board.entity.Board;
import com.linegames.assignment.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;

	// 게시글 조회
	public Page<Board> findByBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1 , pageable.getPageSize());
		System.out.println(pageable.getPageNumber() + " : " + pageable.getPageSize());
		return boardRepository.findAll(pageable);
	}

	// 현재 세션의 사용자 ID 가져오기
	public String currentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getUsername();
	}

}
