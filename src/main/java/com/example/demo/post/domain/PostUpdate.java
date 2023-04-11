package com.example.demo.post.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdate {

    private final String content;

    @Builder
    public PostUpdate(
        @JsonProperty("content") String content) {
        this.content = content;
    }
}
