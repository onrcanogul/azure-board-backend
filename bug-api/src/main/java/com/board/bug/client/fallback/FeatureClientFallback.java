package com.board.bug.client.fallback;


import com.board.bug.client.FeatureClient;
import com.board.bug.utils.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FeatureClientFallback implements FeatureClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Feature Service is not available", 500);
    }
}
