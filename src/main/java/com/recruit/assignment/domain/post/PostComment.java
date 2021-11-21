package com.recruit.assignment.domain.post;

import com.recruit.assignment.domain.post.dto.response.PostCommentResponse;
import com.recruit.assignment.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Post post;

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

    public static PostCommentResponse of(PostComment postComment) {
        return PostCommentResponse.builder()
                .id(postComment.getId())
                .comment(postComment.getComment())
                .isBlocked(postComment.isBlocked())
                .isDeleted(postComment.isDeleted())
                .createUser(User.of(postComment.getCreatedUser()))
                .createDate(postComment.getCreatedDate())
                .modifiedUser(User.of(postComment.getModifiedUser()))
                .modifiedDate(postComment.getModifiedDate())
                .build();
    }

}
