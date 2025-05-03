package com.board.productbacklogitem.command.controller;

import com.board.productbacklogitem.command.command.PbiCreatedCommand;
import com.board.productbacklogitem.command.command.PbiDeleteCommand;
import com.board.productbacklogitem.command.command.PbiUpdateCommand;
import com.board.productbacklogitem.utils.service.NoContent;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api")
public class PbiCommandController {

    private final CommandGateway commandGateway;


    public PbiCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    /**
     *
     * @param model - Create Command Model
     * @return Create Product Backlog Item
     */
    @PostMapping
    public ServiceResponse<NoContent> create(@RequestBody PbiCreatedCommand model) {
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
}
