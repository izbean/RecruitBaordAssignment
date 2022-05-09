package com.board.domain.post.service;

import com.board.domain.post.Post;
import com.board.domain.post.dto.request.PostRequest;
import com.board.domain.post.dto.response.PostResponse;
import com.board.domain.post.exception.PostNotFoundException;
import com.board.domain.post.repository.PostRepository;
import com.board.domain.user.User;
import com.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	private final UserRepository userRepository;

	public Post getPostDetail(long postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException(postId));

		if (post.isDeleted())
			throw new IllegalArgumentException("삭제 된 게시글 입니다.");

		if (post.isBlocked())
			throw new IllegalArgumentException("관리자에 의해 접근 거부 된 게시글 입니다.");

		return post;
	}

	public List<PostResponse> getByBoardList(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1 , pageable.getPageSize());

		return postRepository.findAll(pageable).stream()
				.map(Post::of)
				.collect(Collectors.toList());
	}

	public long getPostCount() {
		return postRepository.count();
	}

	public Long create(PostRequest postRequest, String requestedUserId) {
		User user = userRepository.getOne(requestedUserId);
		return postRepository.save(postRequest.toEntityWithUser(user)).getId();
	}

	public Long update(long id, PostRequest postRequest, String requestedUserId) {
		Post post = checkedAccess(id, requestedUserId);
		User user = post.getCreatedUser();

		post.update(postRequest.getTitle(), postRequest.getCategory(), postRequest.getContents(), user);

		return postRepository.save(post).getId();
	}

	public Long delete(Long boardId, String requestedUserId) {
		Post post = checkedAccess(boardId, requestedUserId);

		post.delete();

		return postRepository.save(post).getId();
	}

	// 작성된 게시글과 요청자의 사용자 ID가 같은지 체크 하기
	private Post checkedAccess(Long boardId, String requestedUserId) {
		Post post = postRepository.findById(boardId)
				.orElseThrow(IllegalArgumentException::new);

		String boardUserId = post.getCreatedUser().getUserId();

		if (!Objects.equals(requestedUserId, boardUserId)) throw new IllegalArgumentException();

		return post;
	}

}
