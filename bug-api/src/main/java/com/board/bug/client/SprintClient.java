package com.board.bug.client;


import com.board.bug.client.fallback.SprintClientFallback;
import com.board.bug.utils.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "sprint-service",url = "${sprint-service.url}",fallback = SprintClientFallback.class)
public interface SprintClient {
    @GetMapping("/isExist/{id}")
    ServiceResponse<Boolean> isExist(@PathVariable UUID id);
}
