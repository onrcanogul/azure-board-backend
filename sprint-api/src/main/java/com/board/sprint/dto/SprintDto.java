package com.board.sprint.dto;

import com.board.sprint.enumaration.SprintState;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SprintDto {
    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String goal;
    private SprintState state;
    private UUID projectId;
    private UUID teamId;
}
