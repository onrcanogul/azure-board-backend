package com.board.feature.service.impl;

import com.board.feature.communication.event.FeatureCompletedEvent;
import com.board.feature.service.FeatureEventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeatureEventPublisherImpl implements FeatureEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;

    public FeatureEventPublisherImpl(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    @Override
    public void publishFeatureCompleted(FeatureCompletedEvent event) throws JsonProcessingException {
        kafkaTemplate.send("feature.completed",  mapper.writeValueAsString(event));
    }
}
