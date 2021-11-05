package com.recruit.assignment.domain.user;

import com.recruit.assignment.domain.board.Board;
import com.recruit.assignment.domain.board.BoardComment;
import com.recruit.assignment.domain.user.dto.UserResponseDto;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User {
	
	@Id
	private String userId;

	@OneToMany(mappedBy = "createdUser")
	private List<Board> createdUserBoards;

	@OneToMany(mappedBy = "modifiedUser")
	private List<Board> modifiedUserBoards;

	@OneToMany(mappedBy = "createdUser")
	private List<BoardComment> createdUserBoardComments;

	@OneToMany(mappedBy = "modifiedUser")
	private List<BoardComment> modifiedUserBoardComments;

	@NotEmpty
	private String password;

	@Enumerated(value = EnumType.STRING)
	private UserRole authority = UserRole.USER;

	private String email;

	private LocalDateTime createDateTime;

	private LocalDateTime modifyDateTime;

	@Builder(builderClassName = "onlyUserIdBuilder", builderMethodName = "onlyUserIdBuilder")
	public User(String userId) {
		this.userId = userId;
	}

	@Builder(builderClassName = "createUserBuilder", builderMethodName = "createUserBuilder")
	public User(String userId, String password, String email) {
		Assert.hasText(userId, "userId must be not empty");
		Assert.hasText(password, "password must be not empty");
		Assert.hasText(email, "email must be not empty");

		this.userId = userId;
		this.password = password;
		this.email = email;
		this.createDateTime = LocalDateTime.now();
	}

	public static UserResponseDto of(User user) {
		return UserResponseDto.builder()
				.userId(user.getUserId())
				.build();
	}
}
