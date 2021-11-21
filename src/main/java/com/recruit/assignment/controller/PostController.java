package com.recruit.assignment.controller;

import com.recruit.assignment.domain.post.dto.request.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.recruit.assignment.domain.post.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Long create(
            @Valid PostRequest postRequest,
            @AuthenticationPrincipal User user
    ) {
        return postService.create(postRequest, user.getUsername());
    }

    @PutMapping("/{id}")
    public Long update(
            @PathVariable long id,
            @Valid PostRequest postRequest,
            @AuthenticationPrincipal User user
    ) {
        return postService.update(id, postRequest, user.getUsername());
    }

    @DeleteMapping("/{id}")
    public Long delete(
            @PathVariable long id,
            @AuthenticationPrincipal User user
    ) {
        return postService.delete(id, user.getUsername());
    }

}
