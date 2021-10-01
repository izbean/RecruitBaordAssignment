package com.recruit.assignment.domain.board.service;

import com.recruit.assignment.domain.board.Board;
import com.recruit.assignment.domain.board.dto.BoardCreateRequestDto;
import com.recruit.assignment.domain.board.dto.BoardUpdateRequestDto;
import com.recruit.assignment.domain.board.exception.BoardContentNotFoundException;
import com.recruit.assignment.domain.board.repository.BoardRepository;
import com.recruit.assignment.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public Board getBoardContentDetail(long boardContentId) {

		Board board = boardRepository.findById(boardContentId)
				.orElseThrow(() -> new BoardContentNotFoundException(boardContentId));

		if (board.isDeleted())
			throw new IllegalArgumentException("삭제 된 게시글 입니다.");

		if (board.isBlocked())
			throw new IllegalArgumentException("관리자에 의해 접근 거부 된 게시글 입니다.");

		return board;
	}

	public Page<Board> getByBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1 , pageable.getPageSize());
		return boardRepository.findAll(pageable);
	}

	public Long create(BoardCreateRequestDto boardRequestDto) {
		return boardRepository.save(boardRequestDto.toEntity()).getId();
	}

	public Long update(BoardUpdateRequestDto boardUpdateRequestDto) {
		String requestedUserId = boardUpdateRequestDto.getUserId();

		Board board = checkedAccess(boardUpdateRequestDto.getBoardId(), requestedUserId);

		board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getCategory(), boardUpdateRequestDto.getContents(), requestedUserId);

		return boardRepository.save(board).getId();
	}

	public Long delete(Long boardId, String requestedUserId) {
		Board board = checkedAccess(boardId, requestedUserId);

		board.delete();

		return boardRepository.save(board).getId();
	}

	// 작성된 게시글과 요청자의 사용자 ID가 같은지 체크 하기
	private Board checkedAccess(Long boardId, String requestedUserId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(IllegalArgumentException::new);

		String boardUserId = board.getCreatedUser().getUserId();

		if (!Objects.equals(requestedUserId, boardUserId)) throw new IllegalArgumentException();

		return board;
	}

}
