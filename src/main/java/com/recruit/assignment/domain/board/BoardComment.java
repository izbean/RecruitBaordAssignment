package com.recruit.assignment.domain.board;

import com.recruit.assignment.domain.board.dto.response.BoardCommentResponseDto;
import com.recruit.assignment.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class BoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String comment;

    private boolean isBlocked;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user")
    private User createdUser;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_user")
    private User modifiedUser;

    private LocalDateTime modifiedDate;

    public static BoardCommentResponseDto of(BoardComment boardComment) {
        return BoardCommentResponseDto.builder()
                .id(boardComment.getId())
                .comment(boardComment.getComment())
                .isBlocked(boardComment.isBlocked())
                .isDeleted(boardComment.isDeleted())
                .createUser(User.of(boardComment.getCreatedUser()))
                .createDate(boardComment.getCreatedDate())
                .modifiedUser(User.of(boardComment.getModifiedUser()))
                .modifiedDate(boardComment.getModifiedDate())
                .build();
    }

}
