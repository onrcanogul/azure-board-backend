package com.board.dashboard.controller;

import com.board.dashboard.dto.DashboardDto;
import com.board.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService service;

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<Mono<DashboardDto>> getBySprint(@PathVariable UUID sprintId) {
        return ResponseEntity.ok(service.getDashboardBySprint(sprintId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Mono<DashboardDto>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getDashboardByUser(userId));
    }
}
