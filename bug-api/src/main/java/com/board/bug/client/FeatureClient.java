package com.board.bug.client;

import com.board.bug.client.fallback.FeatureClientFallback;
import com.board.bug.utils.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "feature-service", url = "${feature-service.url}", fallback = FeatureClientFallback.class)
public interface FeatureClient {
    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id);
}
