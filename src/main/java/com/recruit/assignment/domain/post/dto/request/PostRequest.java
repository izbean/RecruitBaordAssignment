package com.recruit.assignment.domain.post.dto.request;

import com.recruit.assignment.domain.post.Post;
import com.recruit.assignment.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostRequest {

    @NotEmpty(message = "title cannot be empty.")
    private String title;

    @NotEmpty(message = "category cannot be empty.")
    private String category;

    @NotEmpty(message = "contents cannot be empty.")
    private String contents;

    private String userId;

    public Post toEntity() {
        return Post.create()
                .title(title)
                .category(category)
                .contents(contents)
                .build();
    }

    public Post toEntityWithUser(User user) {
        return Post.create()
                .title(title)
                .category(category)
                .contents(contents)
                .user(user)
                .build();
    }

}
