package com.board.epic.client.fallback;

import com.board.epic.client.TeamClient;
import com.board.epic.utils.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TeamClientFallback implements TeamClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Team Service is not available", 503);
    }
}
