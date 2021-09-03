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
	private Long id;

	@OneToOne
	@JoinColumn(name = "board_contents_id")
	private BoardContents boardContents;

	private String title;

	private String contents;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_user")
	private User createdUser;

	private LocalDateTime createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modified_user")
	private User modifiedUser;

	private LocalDateTime modifiedDate;

}
