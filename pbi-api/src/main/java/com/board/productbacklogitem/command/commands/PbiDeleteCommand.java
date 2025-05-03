package com.board.productbacklogitem.command.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class PbiDeleteCommand {
    @TargetAggregateIdentifier
    private UUID id;
}
