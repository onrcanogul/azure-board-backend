package com.board.productbacklogitem.command.controller;

import com.board.productbacklogitem.client.FeatureClient;
import com.board.productbacklogitem.client.SprintClient;
import com.board.productbacklogitem.command.command.PbiCreatedCommand;
import com.board.productbacklogitem.command.command.PbiDeleteCommand;
import com.board.productbacklogitem.command.command.PbiUpdateCommand;
import com.board.productbacklogitem.command.command.PbiUpdateStateCommand;
import com.board.productbacklogitem.utils.service.NoContent;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/pbi")
public class PbiCommandController {

    private final CommandGateway commandGateway;
    private final FeatureClient featureClient;
    private final SprintClient sprintClient;
    public PbiCommandController(
            CommandGateway commandGateway,
            @Qualifier("com.board.productbacklogitem.client.FeatureClient") FeatureClient featureClient,
            @Qualifier("com.board.productbacklogitem.client.SprintClient") SprintClient sprintClient
    ) {
        this.commandGateway = commandGateway;
        this.featureClient = featureClient;
        this.sprintClient = sprintClient;
    }

    /**
     *
     * @param model - Create Command Model
     * @return Create Product Backlog Item
     */
    @PostMapping
    public ServiceResponse<NoContent> create(@RequestBody PbiCreatedCommand model) {
        validations(model.getFeatureId(), model.getSprintId());
        model.setId(UUID.randomUUID());
        commandGateway.sendAndWait(model);
        return ServiceResponse.success(201);
    }

    /**
     *
     * @param model - Update Command Model
     * @return Update Product Backlog Item
     */
    @PutMapping
    public ServiceResponse<NoContent> update(@RequestBody PbiUpdateCommand model) {
        validations(model.getFeatureId(), model.getSprintId());
        commandGateway.sendAndWait(model);
        return ServiceResponse.success(200);
    }

    /**
     *
     * @param model - Update State Model
     * @return Update State of Product Backlog Item
     */
    @PutMapping("/state")
    public ServiceResponse<NoContent> updateState(@RequestBody PbiUpdateStateCommand model) {
        commandGateway.sendAndWait(model);
        return ServiceResponse.success(200);
    }

    /**
     *
     * @param id - Id
     * @return Delete Product Backlog Item by id
     */
    @DeleteMapping("/{id}")
    public ServiceResponse<NoContent> delete(@PathVariable UUID id) {
        PbiDeleteCommand command = new PbiDeleteCommand();
        command.setId(id);
        commandGateway.sendAndWait(command);
        return ServiceResponse.success(200);
    }

    private void validations(UUID featureId, UUID sprintId) {
        ServiceResponse<Boolean> featureExist = featureClient.isExist(featureId);
        if(!featureExist.isSuccessful()) {
            throw new EntityNotFoundException();
        }
        ServiceResponse<Boolean> sprintExist = sprintClient.isExist(sprintId);
        if(!sprintExist.isSuccessful()) {
            throw new EntityNotFoundException();
        }
    }
}
