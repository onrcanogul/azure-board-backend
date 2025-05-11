package com.board.productbacklogitem.entity;

import com.board.productbacklogitem.enumeration.PbiState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Where(clause = "is_deleted = false")
public class ProductBacklogItem {
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
    // Description of the PBI
    private String description;
    // Functional description of the PBI
    private String functionalDescription;
    // Technical description of the PBI
    private String technicalDescription;
    // Priority of the PBI
    private int priority;
    // State of the PBI
    private PbiState state;
    // Story point of the PBI
    private int storyPoint;
    // Business value of the PBI
    private int businessValue;
    // Deadline of the PBI
    private LocalDateTime dueDate;
    // Started date of the PBI
    private LocalDateTime startedDate;
    // Completed date of the PBI
    private LocalDateTime completedDate;
    // Check soft delete
    private boolean IsDeleted;
    @ElementCollection
    // Set of tag ids from Tag service
    private Set<UUID> tagIds = new HashSet<>();
}
