package com.board.bug.entity;

import com.board.bug.enumeration.BugStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Where(clause = "is_deleted = false")
public class Bug {
    //Primary Key
    @Id
    private UUID id;
    // Reference id from Sprint service
    private UUID sprintId;
    // Reference Area id from Area service
    private UUID areaId;
    // Reference Epic id from Epic service
    private UUID featureId;
    // Reference Assigned User id from User service
    private UUID assignedUserId;
    // Description of the Bug
    private String description;
    // Functional description of the Bug
    private String functionalDescription;
    // Technical description of the Bug
    private String technicalDescription;
    // Priority of the Bug
    private int priority;
    // State of the Bug
    private BugStatus status;
    // Story point of the Bug
    private int storyPoint;
    // Business value of the Bug
    private int businessValue;
    // Deadline of the Bug
    private LocalDateTime dueDate;
    // Started date of the Bug
    private LocalDateTime startedDate;
    // Completed date of the Bug
    private LocalDateTime completedDate;
    // No Bug check of the Bug
    private boolean isNoBug;
    // Check soft delete
    private boolean isDeleted;
    @ElementCollection
    // Set of tag ids from Tag service
    private Set<UUID> tagIds = new HashSet<>();
}
