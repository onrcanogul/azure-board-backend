package com.board.productbacklogitem.client.fallback;

import com.board.productbacklogitem.client.SprintClient;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SprintClientFallback implements SprintClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Sprint is not available", 404);
    }
}
