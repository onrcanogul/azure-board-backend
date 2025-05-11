package com.board.productbacklogitem.service.feign.fallback;

import com.board.productbacklogitem.service.feign.FeatureClient;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FeatureClientFallback implements FeatureClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Feature Service is not available", 500);
    }
}
