package com.example.demo.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdate {

    private final String nickname;
    private final String address;

    @Builder
    public UserUpdate(
        @JsonProperty("nickname") String nickname,
        @JsonProperty("address") String address) {
        this.nickname = nickname;
        this.address = address;
    }
}
