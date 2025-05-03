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

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody PbiCreatedCommand model) {
        return commandGateway.send(model);
    }

    @PostMapping("/update")
    public CompletableFuture<String> update(@RequestBody PbiUpdateCommand model) {
        return commandGateway.send(model);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable UUID id) {
        PbiDeleteCommand command = new PbiDeleteCommand();
        return commandGateway.send(command);
    }
}
