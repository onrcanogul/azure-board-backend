package com.board.feature.client;

import com.board.feature.client.fallback.EpicClientFallback;
import com.board.feature.utils.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "epic-service", url = "${epic-service.url}", fallback = EpicClientFallback.class)
public interface EpicClient {
    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id);
}
