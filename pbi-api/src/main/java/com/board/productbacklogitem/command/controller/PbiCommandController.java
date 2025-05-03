package com.board.productbacklogitem.command.controller;

import com.board.productbacklogitem.command.commands.PbiCreatedCommand;
import com.board.productbacklogitem.command.commands.PbiDeleteCommand;
import com.board.productbacklogitem.command.commands.PbiUpdateCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody PbiCreatedCommand model) {
        return commandGateway.send(model);
    }

    /**
     *
     * @param model - Update Command Model
     * @return Update Product Backlog Item
     */
    @PutMapping("/update")
    public CompletableFuture<String> update(@RequestBody PbiUpdateCommand model) {
        return commandGateway.send(model);
    }

    /**
     *
     * @param id - Id
     * @return Delete Product Backlog Item by id
     */
    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable UUID id) {
        PbiDeleteCommand command = new PbiDeleteCommand();
        return commandGateway.send(command);
    }
}
