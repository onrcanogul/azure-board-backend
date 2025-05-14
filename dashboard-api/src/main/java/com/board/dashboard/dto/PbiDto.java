package com.board.dashboard.dto;

import com.board.dashboard.enumeration.PbiStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PbiDto {
    private UUID id;
    private UUID sprintId;
    private UUID areaId;
    private UUID featureId;
    private UUID assignedUserId;
    private String description;
    private String functionalDescription;
    private String technicalDescription;
    private int priority;
    private PbiStatus state;
    private int storyPoint;
    private int businessValue;
    private LocalDateTime dueDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private Set<UUID> tagIds = new HashSet<>();
    private boolean isDeleted;
}
