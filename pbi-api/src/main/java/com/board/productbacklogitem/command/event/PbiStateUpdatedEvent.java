package com.board.productbacklogitem.command.event;

import com.board.productbacklogitem.enumeration.PbiState;
import lombok.Data;

import java.util.UUID;

@Data
public class PbiStateUpdatedEvent {
    private UUID id;
    private PbiState state;
}
