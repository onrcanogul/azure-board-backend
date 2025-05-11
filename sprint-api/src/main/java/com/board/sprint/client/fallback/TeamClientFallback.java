package com.board.sprint.client.fallback;

import com.board.sprint.client.TeamClient;
import com.board.sprint.utils.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamClientFallback implements TeamClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Team service is not available", 500);
    }
}
