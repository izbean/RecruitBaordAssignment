package com.recruit.assignment.domain.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.recruit.assignment.domain.post.dto.response.PostResponse;
import com.recruit.assignment.domain.user.User;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String category;

	@OneToMany(mappedBy = "post")
	private List<PostComment> comments;

	private String title;

	private String contents;

	private boolean isDeleted = false;

	private boolean isBlocked = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_user")
	private User createdUser;

	private LocalDateTime createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_user")
	private User modifiedUser;

	private LocalDateTime modifiedDate;

	public void delete() {
		this.isDeleted = true;
	}

	public void block() { this.isBlocked = true; }

	public void update(String title, String category, String contents, User user) {
		this.title = title;
		this.category = category;
		this.contents = contents;
		this.modifiedUser = user;
		this.modifiedDate = LocalDateTime.now();
	}

	@Builder(builderClassName = "create", builderMethodName = "create")
	public Post(String title, String category, String contents, User user) {
		this.title = title;
		this.category = category;
		this.contents = contents;
		this.createdUser = user;
		this.createdDate = LocalDateTime.now();
	}

	public static PostResponse of(Post post) {
		return PostResponse.builder()
				.id(post.getId())
				.title(post.getTitle())
				.contents(post.getContents())
				.comments(post.getComments().stream()
						.map(PostComment::of)
						.collect(Collectors.toList()))
				.commentCount(post.getComments().size())
				.createUser(User.of(post.getCreatedUser()))
				.modifiedUser(User.of(post.getModifiedUser()))
				.createDateTime(post.getCreatedDate())
				.modifiedDateTime(post.getModifiedDate())
				.build();
	}

	@Override
	public String toString() {
		return "Board{" +
				"id=" + id +
				", category='" + category + '\'' +
				", comments=" + comments +
				", title='" + title + '\'' +
				", contents='" + contents + '\'' +
				", isDeleted=" + isDeleted +
				", isBlocked=" + isBlocked +
				", createdUser=" + createdUser +
				", createdDate=" + createdDate +
				", modifiedUser=" + modifiedUser +
				", modifiedDate=" + modifiedDate +
				'}';
	}
}
