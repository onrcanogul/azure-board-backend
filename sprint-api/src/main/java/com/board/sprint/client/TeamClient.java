package com.board.sprint.client;

import com.board.sprint.client.fallback.TeamClientFallback;
import com.board.sprint.utils.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "team-service", url = "${team-service.url}", fallback = TeamClientFallback.class)
public interface TeamClient {
    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id);
}
