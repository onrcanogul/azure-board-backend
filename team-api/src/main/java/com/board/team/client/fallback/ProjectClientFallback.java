package com.board.team.client.fallback;

import com.board.team.client.ProjectClient;
import com.board.team.util.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectClientFallback implements ProjectClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Project service is not available", 503);
    }
}
