package com.recruit.assignment.user;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.recruit.assignment.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class User {
	
	@Id
	private String userId;

	@OneToMany(mappedBy = "user")
	private ArrayList<Board> boards;

	@NotEmpty
	private String password;

	private UserRole authority = UserRole.USER;

	private String email;

	private LocalDateTime createDateTime;

	private LocalDateTime modifyDateTime;
}
