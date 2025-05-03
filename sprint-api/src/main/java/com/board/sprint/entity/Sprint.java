package com.board.sprint.entity;

import com.board.sprint.enumaration.SprintState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Where(clause = "is_deleted = false")
public class Sprint {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // Name of the Sprint
    private String name;
    // Start Date of the Sprint
    private LocalDate startDate;
    // End Date of the Sprint
    private LocalDate endDate;
    // Goal of the Sprint
    private String goal;
    // State of the Sprint
    private SprintState state;
    // Reference of project from Project Service
    private UUID projectId;
    // Reference of team from Team Service
    private UUID teamId;
    // Soft Delete
    private boolean isDeleted;

}
