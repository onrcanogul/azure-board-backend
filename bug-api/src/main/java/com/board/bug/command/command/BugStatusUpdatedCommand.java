package com.board.bug.command.command;

import com.board.bug.enumeration.BugStatus;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class BugStatusUpdatedCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private BugStatus state;
}
