package com.recruit.assignment.board.service;

import com.recruit.assignment.board.Board;
import com.recruit.assignment.board.exception.BoardContentNotFoundException;
import com.recruit.assignment.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public Board getBoardDetail(int boardId) {
		Optional<Board> boardOpt = boardRepository.findById(boardId);

		if (!boardOpt.isPresent())
			throw new BoardContentNotFoundException(boardId);

		return boardOpt.get();
	}

	public Page<Board> findByBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1 , pageable.getPageSize());
		return boardRepository.findAll(pageable);
	}

	public String currentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getUsername();
	}

}
