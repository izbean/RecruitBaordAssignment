package com.recruit.assignment.domain.board;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.recruit.assignment.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String category;

	@OneToOne(mappedBy = "board")
	private BoardContents boardContents;

	private boolean isDeleted;

	private boolean isBlocked;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_user")
	private User createdUser;

	private LocalDateTime createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_user")
	private User modifiedUser;

	private LocalDateTime modifiedDate;

}
