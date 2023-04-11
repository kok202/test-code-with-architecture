package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateDto {

    private final long writerId;
    private final String content;

    @Builder
    public PostCreateDto(
        @JsonProperty("writerId") long writerId,
        @JsonProperty("content") String content) {
        this.writerId = writerId;
        this.content = content;
    }
}
