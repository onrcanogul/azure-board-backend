package com.board.team.client;

import com.board.team.client.fallback.ProjectClientFallback;
import com.board.team.util.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "project-service", url = "project-service.url", fallback = ProjectClientFallback.class)
public interface ProjectClient {
    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id);
}
