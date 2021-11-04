package com.recruit.assignment.domain.board;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.recruit.assignment.domain.user.User;
import lombok.*;

@Entity
@Getter @ToString
@NoArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String category;

	@OneToMany(mappedBy = "board")
	private List<BoardComment> comments;

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
	public Board(String title, String category, String contents, User user) {
		this.title = title;
		this.category = category;
		this.contents = contents;
		this.createdUser = user;
		this.createdDate = LocalDateTime.now();
	}
}
