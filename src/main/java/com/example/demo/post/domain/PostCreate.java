package com.example.demo.post.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreate {

    private final long writerId;
    private final String content;

    @Builder
    public PostCreate(
        @JsonProperty("writerId") long writerId,
        @JsonProperty("content") String content) {
        this.writerId = writerId;
        this.content = content;
    }
}
