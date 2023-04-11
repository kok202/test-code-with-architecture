package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateDto {

    private final String email;
    private final String nickname;
    private final String address;

    @Builder
    public UserCreateDto(
        @JsonProperty("email") String email,
        @JsonProperty("nickname") String nickname,
        @JsonProperty("address") String address) {
        this.email = email;
        this.nickname = nickname;
        this.address = address;
    }
}
