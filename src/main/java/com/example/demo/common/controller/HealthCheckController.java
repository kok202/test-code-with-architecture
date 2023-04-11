package com.example.demo.common.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "헬스 체크")
@RestController
public class HealthCheckController {

    @GetMapping("/health_check.html")
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity
            .ok()
            .build();
    }
}