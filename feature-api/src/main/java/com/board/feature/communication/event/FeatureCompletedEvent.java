package com.board.feature.communication.event;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class FeatureCompletedEvent {
    private UUID featureId;
}
