package com.board.bug.command.event;

import com.board.bug.enumeration.BugStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class BugStatusUpdatedEvent {
    private UUID id;
    private BugStatus state;
}
