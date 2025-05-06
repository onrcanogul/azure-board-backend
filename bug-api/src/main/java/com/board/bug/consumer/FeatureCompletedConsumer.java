package com.board.bug.consumer;

import com.board.bug.communication.event.FeatureCompletedEvent;
import com.board.bug.entity.Bug;
import com.board.bug.enumeration.BugStatus;
import com.board.bug.repository.BugRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeatureCompletedConsumer {
    private final BugRepository repository;
    private final ObjectMapper objectMapper;

    public FeatureCompletedConsumer(BugRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "feature.completed", groupId = "bug-service")
    public void handle(String payload) throws JsonProcessingException {
        FeatureCompletedEvent event = objectMapper.readValue(payload, FeatureCompletedEvent.class);
        List<Bug> bugs = repository.findByFeatureId(event.getFeatureId());
        for (Bug bug : bugs) {
            bug.setStatus(BugStatus.RESOLVED);
        }
        repository.saveAll(bugs);
    }
}
