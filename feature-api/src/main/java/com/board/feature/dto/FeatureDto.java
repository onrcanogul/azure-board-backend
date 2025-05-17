package com.board.feature.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class FeatureDto {
    private UUID id;
    private String title;
    private String description;
    private UUID teamId;
    private UUID epicId;
    private int priority;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean isCompleted;
    private boolean isDeleted;
}
