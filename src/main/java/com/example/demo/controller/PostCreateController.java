package com.example.demo.controller;

import com.example.demo.model.dto.PostCreateDto;
import com.example.demo.model.dto.PostResponse;
import com.example.demo.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시물(posts)")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostCreateController {

    private final PostService postService;
    private final PostController postController;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateDto postCreateDto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postController.toResponse(postService.create(postCreateDto)));
    }
}