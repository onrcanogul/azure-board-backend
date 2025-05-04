package com.board.bug.command.command;

import com.board.bug.enumeration.BugStatus;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class BugCreatedCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private UUID sprintId;
    private UUID areaId;
    private UUID featureId;
    private UUID assignedUserId;
    private String description;
    private String functionalDescription;
    private String technicalDescription;
    private int priority;
    private BugStatus status;
    private int storyPoint;
    private int businessValue;
    private LocalDateTime dueDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private boolean isNoBug;
    private boolean isDeleted;
    private Set<UUID> tagIds = new HashSet<>();
}
