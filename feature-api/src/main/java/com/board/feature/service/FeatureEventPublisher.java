package com.board.feature.service;

import com.board.feature.communication.event.FeatureCompletedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface FeatureEventPublisher {
    void publishFeatureCompleted(FeatureCompletedEvent event) throws JsonProcessingException;
}
