package com.recruit.assignment.domain.board.service;

import com.recruit.assignment.domain.board.Board;
import com.recruit.assignment.domain.board.dto.request.BoardRequestDto;
import com.recruit.assignment.domain.board.dto.response.BoardResponseDto;
import com.recruit.assignment.domain.board.exception.BoardContentNotFoundException;
import com.recruit.assignment.domain.board.repository.BoardRepository;
import com.recruit.assignment.domain.user.User;
import com.recruit.assignment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	private final UserRepository userRepository;

	public Board getBoardContentDetail(long boardContentId) {

		Board board = boardRepository.findById(boardContentId)
				.orElseThrow(() -> new BoardContentNotFoundException(boardContentId));

		if (board.isDeleted())
			throw new IllegalArgumentException("삭제 된 게시글 입니다.");

		if (board.isBlocked())
			throw new IllegalArgumentException("관리자에 의해 접근 거부 된 게시글 입니다.");

		return board;
	}

	public List<BoardResponseDto> getByBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1 , pageable.getPageSize());

		return boardRepository.findAll(pageable).stream()
				.map(Board::of)
				.collect(Collectors.toList());
	}

	public long getPostCount() {
		return boardRepository.count();
	}

	public Long create(BoardRequestDto boardRequestDto, String requestedUserId) {
		User user = userRepository.getOne(requestedUserId);
		return boardRepository.save(boardRequestDto.toEntityWithUser(user)).getId();
	}

	public Long update(long id, BoardRequestDto boardRequestDto, String requestedUserId) {
		Board board = checkedAccess(id, requestedUserId);
		User user = board.getCreatedUser();

		board.update(boardRequestDto.getTitle(), boardRequestDto.getCategory(), boardRequestDto.getContents(), user);

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
