package com.board.productbacklogitem.consumer;

import com.board.productbacklogitem.communication.event.FeatureCompletedEvent;
import com.board.productbacklogitem.entity.ProductBacklogItem;
import com.board.productbacklogitem.enumeration.PbiState;
import com.board.productbacklogitem.repository.PbiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeatureCompletedConsumer {
    private final PbiRepository repository;
    private final ObjectMapper mapper;

    public FeatureCompletedConsumer(PbiRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "feature.completed", groupId = "pbi-service")
    public void handle(String payload) throws JsonProcessingException {
        FeatureCompletedEvent event = mapper.readValue(payload, FeatureCompletedEvent.class);
        List<ProductBacklogItem> items = repository.findByFeatureId(event.getFeatureId());
        if(items.isEmpty()) {
            return;
        }
        items = items.stream().peek(item -> item.setState(PbiState.RESOLVED)).toList();
        repository.saveAll(items);
    }

}
