package com.board.productbacklogitem.command.command;

import com.board.productbacklogitem.enumeration.PbiState;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class PbiUpdateStateCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private PbiState state;
}
