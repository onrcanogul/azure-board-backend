package com.board.bug.command.controller;

import com.board.bug.client.FeatureClient;
import com.board.bug.client.SprintClient;
import com.board.bug.command.command.BugCreatedCommand;
import com.board.bug.command.command.BugDeletedCommand;
import com.board.bug.command.command.BugUpdatedCommand;
import com.board.bug.configuration.mapper.Mapper;
import com.board.bug.dto.BugDto;
import com.board.bug.utils.NoContent;
import com.board.bug.utils.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bug")
public class BugCommandController {
    private final CommandGateway gateway;
    private final Mapper<BugCreatedCommand, BugDto> createdCommandBugDtoMapper;
    private final Mapper<BugUpdatedCommand, BugDto> updatedCommandBugDtoMapper;
    private final FeatureClient featureClient;
    private final SprintClient sprintClient;



    public BugCommandController(
            CommandGateway gateway,
            Mapper<BugCreatedCommand, BugDto> createdCommandBugDtoMapper,
            Mapper<BugUpdatedCommand, BugDto> updatedCommandBugDtoMapper,
            @Qualifier("com.board.bug.client.FeatureClient") FeatureClient featureClient,
            @Qualifier("com.board.bug.client.SprintClient") SprintClient sprintClient
    ) {
        this.gateway = gateway;
        this.createdCommandBugDtoMapper = createdCommandBugDtoMapper;
        this.updatedCommandBugDtoMapper = updatedCommandBugDtoMapper;
        this.featureClient = featureClient;
        this.sprintClient = sprintClient;
    }

    /**
     *
     * @param model - Create Command Model
     * @return Create Product Backlog Item
     */
    @PostMapping
    public ServiceResponse<BugDto> create(@RequestBody BugCreatedCommand model) {
        validations(model.getFeatureId(), model.getSprintId());
        model.setId(UUID.randomUUID());
        gateway.sendAndWait(model);

        return ServiceResponse.success(createdCommandBugDtoMapper.toDto(model),201);
    }

    /**
     *
     * @param model - Update Command Model
     * @return Update Product Backlog Item
     */
    @PutMapping
    public ServiceResponse<BugDto> update(@RequestBody BugUpdatedCommand model) {
        validations(model.getFeatureId(), model.getSprintId());
        gateway.sendAndWait(model);
        return ServiceResponse.success(updatedCommandBugDtoMapper.toDto(model),200);
    }

    /**
     *
     * @param id - Id
     * @return Delete Product Backlog Item by id
     */
    @DeleteMapping("/{id}")
    public ServiceResponse<NoContent> delete(@PathVariable UUID id) {
        BugDeletedCommand command = new BugDeletedCommand();
        command.setId(id);
        gateway.sendAndWait(command);
        return ServiceResponse.success(200);
    }

    private void validations(UUID featureId, UUID sprintId) {
        ServiceResponse<Boolean> response = featureClient.isExist(featureId);
        if(!response.isSuccessful()) {
            throw new EntityNotFoundException();
        }
        ServiceResponse<Boolean> sprintResponse = sprintClient.isExist(sprintId);
        if(!sprintResponse.isSuccessful()) {
            throw new EntityNotFoundException();
        }
    }
}
