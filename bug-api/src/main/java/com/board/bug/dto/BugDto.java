package com.board.bug.dto;

import com.board.bug.enumeration.BugStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BugDto {
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
