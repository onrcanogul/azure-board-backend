package com.board.bug.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class BugDeletedCommand {
    @TargetAggregateIdentifier
    private UUID id;
}
