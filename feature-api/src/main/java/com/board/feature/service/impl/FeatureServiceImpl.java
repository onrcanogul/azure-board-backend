package com.board.feature.service.impl;

import com.board.feature.communication.event.FeatureCompletedEvent;
import com.board.feature.configuration.mapper.Mapper;
import com.board.feature.dto.FeatureDto;
import com.board.feature.entity.Feature;
import com.board.feature.repository.FeatureRepository;
import com.board.feature.service.FeatureEventPublisher;
import com.board.feature.service.FeatureService;
import com.board.feature.utils.NoContent;
import com.board.feature.utils.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeatureServiceImpl implements FeatureService {
    private final FeatureRepository repository;
    private final Mapper<Feature, FeatureDto> mapper;
    private final FeatureEventPublisher eventPublisher;

    public FeatureServiceImpl(FeatureRepository repository, Mapper<Feature, FeatureDto> mapper, FeatureEventPublisher eventPublisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public ServiceResponse<FeatureDto> getById(UUID id) {
        return ServiceResponse
                .success(mapper.toDto(repository.findById(id).orElseThrow()),200);
    }

    @Override
    public ServiceResponse<List<FeatureDto>> getByArea(UUID areaId) {
        return ServiceResponse
                .success(repository.findByAreaId(areaId).stream().map(mapper::toDto).toList(), 200);
    }

    @Override
    public ServiceResponse<List<FeatureDto>> getByEpic(UUID epicId) {
        return ServiceResponse
                .success(repository.findByEpicId(epicId).stream().map(mapper::toDto).toList(), 200);
    }

    @Override
    public ServiceResponse<FeatureDto> create(FeatureDto model) {
        Feature feature = mapper.toEntity(model);
        feature.setId(null);
        Feature createdFeature = repository.save(feature);
        return ServiceResponse.success(mapper.toDto(createdFeature), 200);
    }

    @Override
    public ServiceResponse<FeatureDto> update(FeatureDto model) {
        Feature feature = repository.findById(model.getId()).orElseThrow();
        feature.setTitle(model.getTitle());
        feature.setPriority(model.getPriority());
        Feature updatedFeature = repository.save(feature);
        return ServiceResponse.success(mapper.toDto(updatedFeature), 200);
    }

    @Override
    public ServiceResponse<NoContent> complete(UUID id) throws JsonProcessingException {
        Feature feature = repository.findById(id).orElseThrow();
        feature.setCompleted(true);
        repository.save(feature);
        publishCompletedEvent(feature.getId());
        return ServiceResponse.success(204);
    }

    @Override
    public ServiceResponse<NoContent> delete(UUID id) {
        Feature feature = repository.findById(id).orElseThrow();
        feature.setDeleted(true);
        repository.save(feature);
        return ServiceResponse.success(200);
    }

    private void publishCompletedEvent(UUID featureId) throws JsonProcessingException {
        FeatureCompletedEvent featureCompletedEvent = new FeatureCompletedEvent(featureId);
        eventPublisher.publishFeatureCompleted(featureCompletedEvent);
    }
}
