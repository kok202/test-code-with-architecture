package com.example.demo.controller;

import com.example.demo.model.dto.MyProfileResponse;
import com.example.demo.model.dto.UserResponse;
import com.example.demo.model.dto.UserUpdateDto;
import com.example.demo.repository.UserEntity;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return ResponseEntity
            .ok()
            .body(toResponse(userService.getById(id)));
    }

    @GetMapping("/{id}/verify")
    public ResponseEntity<Void> verifyEmail(
        @PathVariable long id,
        @RequestParam String certificationCode) {
        userService.verifyEmail(id, certificationCode);
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("http://localhost:3000"))
            .build();
    }

    @GetMapping("/me")
    public ResponseEntity<MyProfileResponse> getMyInfo(
        @Parameter(name = "EMAIL", in = ParameterIn.HEADER)
        @RequestHeader("EMAIL") String email // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
    ) {
        UserEntity userEntity = userService.getByEmail(email);
        userService.login(userEntity.getId());
        return ResponseEntity
            .ok()
            .body(toMyProfileResponse(userEntity));
    }

    @PutMapping("/me")
    @Parameter(in = ParameterIn.HEADER, name = "EMAIL")
    public ResponseEntity<MyProfileResponse> updateMyInfo(
        @Parameter(name = "EMAIL", in = ParameterIn.HEADER)
        @RequestHeader("EMAIL") String email, // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
        @RequestBody UserUpdateDto userUpdateDto
    ) {
        UserEntity userEntity = userService.getByEmail(email);
        userEntity = userService.update(userEntity.getId(), userUpdateDto);
        return ResponseEntity
            .ok()
            .body(toMyProfileResponse(userEntity));
    }

    public UserResponse toResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setNickname(userEntity.getNickname());
        userResponse.setStatus(userEntity.getStatus());
        userResponse.setLastLoginAt(userEntity.getLastLoginAt());
        return userResponse;
    }

    public MyProfileResponse toMyProfileResponse(UserEntity userEntity) {
        MyProfileResponse myProfileResponse = new MyProfileResponse();
        myProfileResponse.setId(userEntity.getId());
        myProfileResponse.setEmail(userEntity.getEmail());
        myProfileResponse.setNickname(userEntity.getNickname());
        myProfileResponse.setStatus(userEntity.getStatus());
        myProfileResponse.setAddress(userEntity.getAddress());
        myProfileResponse.setLastLoginAt(userEntity.getLastLoginAt());
        return myProfileResponse;
    }
}