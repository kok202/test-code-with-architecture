package com.example.demo.user.controller.response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

public class MyProfileResponseTest {

    @Test
    public void User으로_응답을_생성할_수_있다() {
        // given
        User user = User.builder()
            .id(1L)
            .email("kok202@naver.com")
            .nickname("kok202")
            .address("Seoul")
            .status(UserStatus.ACTIVE)
            .lastLoginAt(100L)
            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
            .build();

        // when
        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);

        // then
        assertThat(myProfileResponse.getId()).isEqualTo(1);
        assertThat(myProfileResponse.getEmail()).isEqualTo("kok202@naver.com");
        assertThat(myProfileResponse.getAddress()).isEqualTo("Seoul");
        assertThat(myProfileResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(myProfileResponse.getLastLoginAt()).isEqualTo(100L);
    }
}
