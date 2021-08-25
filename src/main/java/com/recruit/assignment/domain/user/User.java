package com.recruit.assignment.domain.user;

import com.recruit.assignment.domain.board.Board;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class User {
	
	@Id
	private String userId;

	@OneToMany(mappedBy = "createdUser")
	private List<Board> createdUserBoards;

	@OneToMany(mappedBy = "modifiedUser")
	private List<Board> modifiedUserBoards;

	@NotEmpty
	private String password;

	private UserRole authority = UserRole.USER;

	private String email;

	private LocalDateTime createDateTime;

	private LocalDateTime modifyDateTime;

	public User(String userId) {
		this.userId = userId;
	}

	@Builder
	public User(String userId, String password, String email) {
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.createDateTime = LocalDateTime.now();
	}
}
