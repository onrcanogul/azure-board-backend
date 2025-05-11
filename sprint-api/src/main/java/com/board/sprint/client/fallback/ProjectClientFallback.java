package com.board.sprint.client.fallback;

import com.board.sprint.client.ProjectClient;
import com.board.sprint.utils.ServiceResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectClientFallback implements ProjectClient {
    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.failure("Project service is not available", 500);
    }
}
