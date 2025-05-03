package com.board.productbacklogitem.command.event;

import lombok.Data;

import java.util.UUID;

@Data
public class PbiDeletedEvent {
    private UUID id;
}
