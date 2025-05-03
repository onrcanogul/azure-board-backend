package com.board.productbacklogitem.command.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class PbiCreatedCommand {
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
    private String state;
    private int storyPoint;
    private int businessValue;
    private LocalDateTime dueDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private Set<UUID> tagIds = new HashSet<>();
    private boolean isDeleted;
}
