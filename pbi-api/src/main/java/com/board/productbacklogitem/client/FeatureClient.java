package com.board.productbacklogitem.client;

import com.board.productbacklogitem.client.fallback.FeatureClientFallback;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "feature-service", url = "${feature-service.url}", fallback = FeatureClientFallback.class)
public interface FeatureClient {
    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id);
}
