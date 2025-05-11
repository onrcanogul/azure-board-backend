package com.board.feature.client.fallback;

import com.board.feature.client.EpicClient;
import com.board.feature.utils.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EpicClientFallback implements EpicClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Epic service is not available", 503);
    }
}
