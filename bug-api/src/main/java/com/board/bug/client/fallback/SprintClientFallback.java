package com.board.bug.client.fallback;


import com.board.bug.client.SprintClient;
import com.board.bug.utils.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SprintClientFallback implements SprintClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Sprint is not available", 404);
    }
}
